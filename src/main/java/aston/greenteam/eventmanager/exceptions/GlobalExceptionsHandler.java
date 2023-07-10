package aston.greenteam.eventmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionsHandler {
//
//    @ExceptionHandler
//    public ResponseEntity<AppError> handleUsernameNotFoundException(UsernameNotFoundException e) {
//        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(FileWasNotSavedException.class)
    public ResponseEntity<ErrorMessage> handleFileNotSavedException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEventNotFoundException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));
    }


    class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }
    }

}