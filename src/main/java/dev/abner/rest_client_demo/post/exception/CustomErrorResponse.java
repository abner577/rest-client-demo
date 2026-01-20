package dev.abner.rest_client_demo.post.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record CustomErrorResponse(LocalDateTime time, int status, String message) {
}
