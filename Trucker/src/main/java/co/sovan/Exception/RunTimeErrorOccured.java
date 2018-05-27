package co.sovan.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RunTimeErrorOccured extends RuntimeException {
    public RunTimeErrorOccured(String message){
        super(message);
            }
}
