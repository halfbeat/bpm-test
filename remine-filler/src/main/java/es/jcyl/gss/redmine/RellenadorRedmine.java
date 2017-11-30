package es.jcyl.gss.redmine;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.TimeEntry;
import com.taskadapter.redmineapi.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RellenadorRedmine {
    private static final Logger logger = LoggerFactory.getLogger(RellenadorRedmine.class);

    private final List<DayOfWeek> diasSemana;
    private final RedmineManager gestorRedmine;
    private final InformacionRellenoRedmine informacion;

    RellenadorRedmine(RedmineManager gestorRedmine, InformacionRellenoRedmine informacion) {
        // La información no puede ser nula
        Objects.requireNonNull(informacion, "La información para rellenar no puede ser nula");

        this.gestorRedmine = gestorRedmine;
        this.informacion = informacion;

        Map<DiaSemana, DayOfWeek> mapaDiasSemana = Arrays.stream(DiaSemana.values())
                .collect(Collectors.toMap(x -> x, x -> DayOfWeek.of(x.ordinal() + 1)));
        diasSemana = informacion.getDiasSemana().stream().map(mapaDiasSemana::get).collect(Collectors.toList());
    }

    void rellenar() throws RedmineException {
        logger.debug("Validando los datos de entrada");
        validarDatos();
        Collection<TimeEntry> entradas = construirEntradasRedmine();
        logger.debug("Generada(s) {} entrada(s) de tiempo. Se procede a enviarla(s) a redmine");
        int idxEntada = 0;
        for (TimeEntry entrada : entradas) {
            gestorRedmine.createTimeEntry(entrada);
            logger.debug("Entrada {} enviada.", idxEntada);
            idxEntada++;
        }
    }

    private Collection<TimeEntry> construirEntradasRedmine() throws RedmineException {
        LocalDate start = informacion.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = informacion.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<LocalDate> listaDias = Stream.iterate(start, d -> d.plusDays(1))
                .limit(start.until(end.plusDays(1), ChronoUnit.DAYS))
                .filter(d -> diasSemana.contains(d.getDayOfWeek()))
                .collect(Collectors.toList());
        double minimoPeriodoAsignable = informacion.getMinPeriodoHorasAsignable()
                .orElse(InformacionRellenoRedmine.DEFAULT_MIN_PERIODO_ASIGNABLE);
        double maximoPeriodoAsignable = informacion.getMaxPeriodoHorasAsignable()
                .orElse(InformacionRellenoRedmine.DEFAULT_MAX_PERIODO_ASIGNABLE);
        double piezasDia = informacion.getHorasDiarias() / minimoPeriodoAsignable;

        Map<Proyecto, Project> mapaProyectosRedmine = informacion.getProyectos().stream()
                .collect(Collectors.toMap(Function.identity(), this::obtenerPRoyectoRedmine));

        logger.debug("Recuperando el usuario actual");
        User usuarioActual = gestorRedmine.getCurrentUser();
        logger.debug("Usuario: {}", usuarioActual);

        List<TimeEntry> resultado = new ArrayList<>();
        Random r = new Random();
        RandomCollection<Proyecto> generadorAleatorioProyectos = new RandomCollection<>();
        Map<Proyecto, RandomCollection<TipoActividad>> mapaGeneradoresActividadesProyecto = new HashMap<>();
        for (Proyecto proyecto : informacion.getProyectos()) {
            RandomCollection<TipoActividad> generador = new RandomCollection<>(r);
            int index = 0;
            for (TipoActividad actividad : proyecto.getActividades()) {
                generador.add(proyecto.getPesosActividades().get(index), actividad);
                index++;
                if (index >= proyecto.getPesosActividades().size()) {
                    index = 0;
                }
            }
            mapaGeneradoresActividadesProyecto.put(proyecto, generador);
            generadorAleatorioProyectos.add(proyecto.getPeso().orElse(1.0), proyecto);
        }
        for (LocalDate fecha : listaDias) {
            logger.debug("Dia {}", fecha.format(DateTimeFormatter.ISO_LOCAL_DATE));
            int restante = (int) piezasDia;

            while (restante > 0) {
                int periodo = r.nextInt(restante + 1);
                if (periodo == 0 || periodo > maximoPeriodoAsignable / minimoPeriodoAsignable) {
                    continue;
                }
                Proyecto proyecto = generadorAleatorioProyectos.next();
                TipoActividad actividad = mapaGeneradoresActividadesProyecto.get(proyecto).next();
                logger.debug("A la actividad {} del proyecto {} se le imputarán {} horas",
                        new Object[]{actividad, proyecto.getId(), periodo * minimoPeriodoAsignable});
                TimeEntry imputacion = new TimeEntry();
                imputacion.setProjectId(mapaProyectosRedmine.get(proyecto).getId());
                imputacion.setActivityId(actividad.getValue());
                imputacion.setComment("Prueba");
                imputacion.setHours((float) (periodo * minimoPeriodoAsignable));
                imputacion.setCreatedOn(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                imputacion.setSpentOn(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                resultado.add(imputacion);
                restante -= periodo;
            }
        }

        return resultado;
    }

    private Project obtenerPRoyectoRedmine(Proyecto proyecto) {
        try {
            return gestorRedmine.getProjectByKey(proyecto.getId());
        } catch (RedmineException e) {
            throw new RuntimeException(e);
        }
    }

    private void validarDatos() {
        // La fecha de inicio es obligatoria
        Objects.requireNonNull(informacion.getFechaInicio(), "La fecha de inicio no puede ser nula");
        // La fecha de fin es obligatoria
        Objects.requireNonNull(informacion.getFechaFin(), "La fecha de fin no puede ser nula");
        // La fecha de inicio debe de ser anterior a la de fin
        if (informacion.getFechaFin().before(informacion.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la de fin");
        }

    }
}
