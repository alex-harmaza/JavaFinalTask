package by.training.epam.lab9.bean.response;

/**
 * Created by alexh on 26.10.2016.
 */
public class Response {

    private boolean status;


    public Response(){
        setStatus(true);
    }

    public Response(boolean status){
        setStatus(status);
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
