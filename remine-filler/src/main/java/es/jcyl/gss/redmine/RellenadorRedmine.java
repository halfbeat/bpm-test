package es.jcyl.gss.redmine;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.TimeEntry;
import com.taskadapter.redmineapi.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.SequenceInputStream;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RellenadorRedmine {
    private final List<DayOfWeek> diasSemana;
    private Map<DiaSemana, DayOfWeek> mapaDiasSemana =
            Arrays.stream(DiaSemana.values()).collect(Collectors.toMap(x -> x, x -> DayOfWeek.of(x.ordinal() + 1)));
    private static final Logger logger = LoggerFactory.getLogger(RellenadorRedmine.class);
    private final RedmineManager gestorRedmine;
    private User usuarioActual;
    private Map<Proyecto, Project> mapaProyectos = Collections.emptyMap();
    private final InformacionRellenoRedmine informacion;

    RellenadorRedmine(RedmineManager gestorRedmine, InformacionRellenoRedmine informacion) {
        // La información no puede ser nula
        Objects.requireNonNull(informacion, "La información para rellenar no puede ser nula");

        this.gestorRedmine = gestorRedmine;
        this.informacion = informacion;

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
        // TODO: Implementar el algoritmo
        /**
         * Dividimos el periodo en unidades de {minPeriodoHorasAsignable}
         */

        LocalDate start = informacion.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = informacion.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Stream<LocalDate> diasStream = Stream.iterate(start, d -> d.plusDays(1))
                .limit(start.until(end, ChronoUnit.DAYS))
                .filter(d -> diasSemana.contains(d.getDayOfWeek()));
        double dPiezas = diasStream.count() * informacion.getHorasDiarias() / informacion.getMinPeriodoHorasAsignable()
                .orElse(InformacionRellenoRedmine.DEFAULT_MIN_PERIODO_ASIGNABLE);
        int piezas = ((int) Math.floor(dPiezas));
        logger.debug("Nº de piezas = {}", piezas);

        RandomCollection<String> r = new RandomCollection<>();
        r.add(10.0, "10").add(5.0, "5");

        Map<String, Long> counted = Stream.generate(r).limit(10000)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(counted);

        for (Proyecto p : informacion.getProyectos()) {
            int index = 0;
            for (TipoActividad a : p.getActividades()) {
                //double peso = p.getPeso().map(w->w*p.getPesosActividades())
                index++;
            }
        }

        logger.debug("Recuperando el usuario actual");
        usuarioActual = gestorRedmine.getCurrentUser();
        logger.debug("Usuario: {}", usuarioActual);
        for (Proyecto proyecto : informacion.getProyectos()) {
            mapaProyectos.put(proyecto, gestorRedmine.getProjectByKey(proyecto.getId()));
            logger.debug("Recuperada información del proyecto {}", proyecto.getId());
        }
        return Collections.emptyList();
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

    public User getUsuarioActual() {
        return usuarioActual;
    }

}
