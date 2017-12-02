package es.jcyl.gss.redmine;

import java.util.List;

class Proyecto {
    private String id;
    private List<TipoActividad> actividades;
    private List<Double> pesosActividades;
    private Double peso ;
    private Double minHorasSemanales ;
    private Double maxHorasSemanales ;

    public Proyecto() {
    }

    public Proyecto(String id, List<TipoActividad> actividades, Double peso) {
        this.id = id;
        this.actividades = actividades;
        this.peso = peso;
    }

    String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    List<TipoActividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<TipoActividad> actividades) {
        this.actividades = actividades;
    }

    Double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    List<Double> getPesosActividades() {
        return pesosActividades;
    }

    public void setPesosActividades(List<Double> pesosActividades) {
        this.pesosActividades = pesosActividades;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getMinHorasSemanales() {
        return minHorasSemanales;
    }

    public void setMinHorasSemanales(Double minHorasSemanales) {
        this.minHorasSemanales = minHorasSemanales;
    }

    public Double getMaxHorasSemanales() {
        return maxHorasSemanales;
    }

    public void setMaxHorasSemanales(Double maxHorasSemanales) {
        this.maxHorasSemanales = maxHorasSemanales;
    }

    // TODO: Crear los métodos toString, equals y hashCode


    @Override
    public String toString() {
        return "Proyecto{" +
                "id='" + id + '\'' +
                ", actividades=" + actividades.toString() +
                ", pesosActividades=" + actividades.toString() +
                ", peso=" + peso +
                ", minHorasSemanales=" + minHorasSemanales +
                ", maxHorasSemanales=" + maxHorasSemanales +
                '}';
    }
}
