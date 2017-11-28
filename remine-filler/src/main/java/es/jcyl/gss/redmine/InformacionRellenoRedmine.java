package es.jcyl.gss.redmine;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InformacionRellenoRedmine {
    public static final double DEFAULT_MIN_PERIODO_ASIGNABLE = 1;
    public static final double DEFAULT_MAX_PERIODO_ASIGNABLE = 1;
    private Date fechaInicio;
    private Date fechaFin;
    private Date[] ignorarFechas;
    private List<DiaSemana> diasSemana;
    private Double horasDiarias;
    private List<Proyecto> proyectos;
    private Optional<Double> minPeriodoHorasAsignable;
    private Optional<Double> maxPeriodoHorasAsignable;

    public InformacionRellenoRedmine() {
    }

    public InformacionRellenoRedmine(Date fechaInicio, Date fechaFin, Date[] ignorarFechas, List<DiaSemana> diasSemana, Double horasDiarias,
                                     List<Proyecto> proyectos) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ignorarFechas = ignorarFechas;
        this.diasSemana = diasSemana;
        this.horasDiarias = horasDiarias;
        this.proyectos = proyectos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date[] getIgnorarFechas() {
        return ignorarFechas;
    }

    public void setIgnorarFechas(Date[] ignorarFechas) {
        this.ignorarFechas = ignorarFechas;
    }

    public List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public Double getHorasDiarias() {
        return horasDiarias;
    }

    public void setHorasDiarias(Double horasDiarias) {
        this.horasDiarias = horasDiarias;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Optional<Double> getMinPeriodoHorasAsignable() {
        return minPeriodoHorasAsignable;
    }

    public void setMinPeriodoHorasAsignable(Double minPeriodoHorasAsignable) {
        this.minPeriodoHorasAsignable = Optional.ofNullable(minPeriodoHorasAsignable);
    }

    public Optional<Double> getMaxPeriodoHorasAsignable() {
        return maxPeriodoHorasAsignable;
    }

    public void setMaxPeriodoHorasAsignable(Double maxPeriodoHorasAsignable) {
        this.maxPeriodoHorasAsignable = Optional.ofNullable(maxPeriodoHorasAsignable);
    }

    // TODO: Crear los métodos toString, equals y hashCode

    @Override
    public String toString() {
        String prjs = "";
        if (proyectos != null && proyectos.size() != 0) {
            for (int idx = 0; idx < proyectos.size() - 1; idx++) {
                prjs = prjs + "\n\t\t" + proyectos.get(idx) + ",";
            }
            prjs = prjs + "\n\t\t" + proyectos.get(proyectos.size() - 1);
        }
        return "InformacionRellenoRedmine{" +
                "\n\tfechaInicio=" + fechaInicio + "," +
                "\n\tfechaFin=" + fechaFin + "," +
                "\n\tignorarFechas=" + Arrays.toString(ignorarFechas) + "," +
                "\n\tminPeriodoHorasAsignable=" + minPeriodoHorasAsignable + "," +
                "\n\tmaxPeriodoHorasAsignable=" + maxPeriodoHorasAsignable + "," +
                "\n\tdiasSemana=" + diasSemana.toString() + "," +
                "\n\thorasDiarias=" + horasDiarias + "," +
                "\n\tproyectos=[" + prjs +
                "\n\t]\n}";
    }
}
