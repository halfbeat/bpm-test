package es.jcyl.gss.redmine;

// TODO: ELiminar si no se usa
public class DatosInvalidosException extends Exception {
    public DatosInvalidosException() {
    }

    public DatosInvalidosException(String message) {
        super(message);
    }

    public DatosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatosInvalidosException(Throwable cause) {
        super(cause);
    }

    public DatosInvalidosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
