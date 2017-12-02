package es.jcyl.gss.redmine;

import java.util.Date;
import java.util.List;

class InformacionRellenoRedmine {
    static final double DEFAULT_MIN_PERIODO_ASIGNABLE = 1;
    static final double DEFAULT_MAX_PERIODO_ASIGNABLE = 1;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Date> ignorarFechas;
    private List<DiaSemana> diasSemana;
    private Double horasDiarias;
    private List<Proyecto> proyectos;
    private Double minPeriodoHorasAsignable;
    private Double maxPeriodoHorasAsignable;
    private List<Imputacion> imputaciones;

    public InformacionRellenoRedmine() {
    }

    public InformacionRellenoRedmine(Date fechaInicio, Date fechaFin, List<Date> ignorarFechas, List<DiaSemana> diasSemana, Double horasDiarias,
                                     List<Proyecto> proyectos) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ignorarFechas = ignorarFechas;
        this.diasSemana = diasSemana;
        this.horasDiarias = horasDiarias;
        this.proyectos = proyectos;
    }

    Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    List<Date> getIgnorarFechas() {
        return ignorarFechas;
    }

    public void setIgnorarFechas(List<Date> ignorarFechas) {
        this.ignorarFechas = ignorarFechas;
    }

    List<DiaSemana> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<DiaSemana> diasSemana) {
        this.diasSemana = diasSemana;
    }

    Double getHorasDiarias() {
        return horasDiarias;
    }

    public void setHorasDiarias(Double horasDiarias) {
        this.horasDiarias = horasDiarias;
    }

    List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    Double getMinPeriodoHorasAsignable() {
        return minPeriodoHorasAsignable;
    }

    public void setMinPeriodoHorasAsignable(Double minPeriodoHorasAsignable) {
        this.minPeriodoHorasAsignable = minPeriodoHorasAsignable;
    }

    Double getMaxPeriodoHorasAsignable() {
        return maxPeriodoHorasAsignable;
    }

    public void setMaxPeriodoHorasAsignable(Double maxPeriodoHorasAsignable) {
        this.maxPeriodoHorasAsignable = maxPeriodoHorasAsignable;
    }

    List<Imputacion> getImputaciones() {
        return imputaciones;
    }

    public void setImputaciones(List<Imputacion> imputaciones) {
        this.imputaciones = imputaciones;
    }

    // TODO: Crear los métodos toString, equals y hashCode

    @Override
    public String toString() {
        StringBuilder prjs = new StringBuilder();
        if (proyectos != null && proyectos.size() != 0) {
            for (int idx = 0; idx < proyectos.size() - 1; idx++) {
                prjs.append("\n\t\t").append(proyectos.get(idx)).append(",");
            }
            prjs.append("\n\t\t").append(proyectos.get(proyectos.size() - 1));
        }
        return "InformacionRellenoRedmine{" +
                "\n\tfechaInicio=" + fechaInicio + "," +
                "\n\tfechaFin=" + fechaFin + "," +
                "\n\tignorarFechas=" + ignorarFechas + "," +
                "\n\tminPeriodoHorasAsignable=" + minPeriodoHorasAsignable + "," +
                "\n\tmaxPeriodoHorasAsignable=" + maxPeriodoHorasAsignable + "," +
                "\n\tdiasSemana=" + diasSemana.toString() + "," +
                "\n\timputaciones=" + imputaciones.toString() + "," +
                "\n\thorasDiarias=" + horasDiarias + "," +
                "\n\tproyectos=[" + prjs +
                "\n\t]\n}";
    }
}
