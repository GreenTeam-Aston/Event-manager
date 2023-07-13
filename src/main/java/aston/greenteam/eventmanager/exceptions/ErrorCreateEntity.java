package aston.greenteam.eventmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ErrorCreateEntity extends RuntimeException{
    public ErrorCreateEntity() {
        super("Error create entity");
    }
}
