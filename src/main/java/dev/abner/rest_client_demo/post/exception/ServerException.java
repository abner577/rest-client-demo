package dev.abner.rest_client_demo.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ServerException extends RuntimeException {
    public ServerException(String message) {
        super(message);
    }
}
