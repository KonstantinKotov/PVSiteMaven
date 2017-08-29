package dao;

/**
 * Created by k.kotov on 19.07.2017.
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(Throwable e){
        initCause(e);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DAOException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
