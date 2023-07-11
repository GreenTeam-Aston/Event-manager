package aston.greenteam.eventmanager.mappers;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class ResponseEntityMapper {
    public static ResponseEntity<Resource> toEntity(Resource resource) {
        ResponseEntity<Resource> responseEntity;
        try {
            responseEntity = ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }

}
