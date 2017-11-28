package es.jcyl.gss.redmine;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Proyecto {
    private String id;
    private List<TipoActividad> actividades;
    private List<Double> pesosActividades;
    private Optional<Double> peso = Optional.empty();
    private Optional<Double> minHorasSemanales = Optional.empty();
    private Optional<Double> maxHorasSemanales = Optional.empty();

    public Proyecto() {
    }

    public Proyecto(String id, List<TipoActividad> actividades, Double peso) {
        this.id = id;
        this.actividades = actividades;
        this.peso = Optional.ofNullable(peso);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TipoActividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<TipoActividad> actividades) {
        this.actividades = actividades;
    }

    public Optional<Double> getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = Optional.ofNullable(peso);
    }

    public List<Double> getPesosActividades() {
        return pesosActividades;
    }

    public void setPesosActividades(List<Double> pesosActividades) {
        this.pesosActividades = pesosActividades;
    }

    public void setPeso(Optional<Double> peso) {
        this.peso = peso;
    }

    public Optional<Double> getMinHorasSemanales() {
        return minHorasSemanales;
    }

    public void setMinHorasSemanales(Double minHorasSemanales) {
        this.minHorasSemanales = Optional.ofNullable(minHorasSemanales);
    }

    public Optional<Double> getMaxHorasSemanales() {
        return maxHorasSemanales;
    }

    public void setMaxHorasSemanales(Double maxHorasSemanales) {
        this.maxHorasSemanales = Optional.ofNullable(maxHorasSemanales);
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
