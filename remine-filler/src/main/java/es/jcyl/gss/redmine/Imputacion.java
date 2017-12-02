package es.jcyl.gss.redmine;

import java.util.Date;

class Imputacion {
    private String proyecto;
    private TipoActividad actividad;
    private String texto;
    private Date fecha;
    private double tiempo;

    public Imputacion() {
    }

    public Imputacion(String proyecto, TipoActividad actividad, String texto, Date fecha, double tiempo) {
        this.proyecto = proyecto;
        this.actividad = actividad;
        this.texto = texto;
        this.fecha = fecha;
        this.tiempo = tiempo;
    }

    String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    TipoActividad getActividad() {
        return actividad;
    }

    public void setActividad(TipoActividad actividad) {
        this.actividad = actividad;
    }

    String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imputacion that = (Imputacion) o;

        if (Double.compare(that.tiempo, tiempo) != 0) return false;
        if (proyecto != null ? !proyecto.equals(that.proyecto) : that.proyecto != null) return false;
        if (actividad == that.actividad) if (texto != null ? texto.equals(that.texto) : that.texto == null)
            if (fecha != null ? fecha.equals(that.fecha) : that.fecha == null) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = proyecto != null ? proyecto.hashCode() : 0;
        result = 31 * result + (actividad != null ? actividad.hashCode() : 0);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        temp = Double.doubleToLongBits(tiempo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
