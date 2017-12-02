package es.jcyl.gss.redmine;

public enum TipoActividad {
    Analisis(10), Desarrollo(9), Pruebas(11), Formacion(15), Soporte(17), Gestion(13), Reunion(34);

    private final int value;

    TipoActividad(int i) {
        this.value = i;
    }


    public int getValue() {
        return value;
    }
}
