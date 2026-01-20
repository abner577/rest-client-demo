package dev.abner.rest_client_demo.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Returned customErrorResponse for PostNotFoundException &
 * returned manual body in ServerException to showcase both ways to handle exceptions
 * at the end of the day, all you need to return is a ResponseEntity<>(body, status)
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Object> handle404NotFound(PostNotFoundException exception) {
         var customErrorResponse = new CustomErrorResponse(
                 LocalDateTime.now(),
                 HttpStatus.NOT_FOUND.value(),
                 exception.getMessage()
         );
         return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);

        /**
         * Diff way of doing the same thing:
         * return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
         */

    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Object> handle500ServerError(ServerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("error", exception.getMessage()));

        /**
         *  Diff way of doing the same thing:
         *  return new ResponseEntity<>(Map.of("error", exception.getMessage()), HttpStatus.BAD_GATEWAY);
         */
    }


}
