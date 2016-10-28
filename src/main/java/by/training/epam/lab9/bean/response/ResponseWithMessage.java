package by.training.epam.lab9.bean.response;

/**
 * Created by alexh on 26.10.2016.
 */
public class ResponseWithMessage extends Response {

    private String message;


    public ResponseWithMessage(String message){
        super();
        setMessage(message);
    }

    public ResponseWithMessage(boolean status, String message){
        this(message);
        setStatus(status);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
