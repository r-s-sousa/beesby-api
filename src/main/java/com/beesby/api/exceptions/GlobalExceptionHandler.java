package com.beesby.api.exceptions;

import com.beesby.api.utils.Message;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException() {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, Message.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ProblemDetail handleForbiddenException() {
        return buildProblemDetail(HttpStatus.FORBIDDEN, Message.FORBIDDEN_OPERATION);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException() {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ProblemDetail handleInternalServerErrorException() {
        return buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException() {
        return buildProblemDetail(HttpStatus.NOT_FOUND, Message.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorizedException() {
        return buildProblemDetail(HttpStatus.UNAUTHORIZED, Message.UNAUTHORIZED_ACCESS);
    }

    // Specific Exception Handlers for Security-Related Exceptions

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException() {
        return buildProblemDetail(HttpStatus.UNAUTHORIZED, Message.BAD_CREDENTIALS);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ProblemDetail handleAccountStatusException() {
        return buildProblemDetail(HttpStatus.FORBIDDEN, Message.ACCOUNT_LOCKED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException() {
        return buildProblemDetail(HttpStatus.FORBIDDEN, Message.ACCESS_DENIED);
    }

    @ExceptionHandler(SignatureException.class)
    public ProblemDetail handleSignatureException() {
        return buildProblemDetail(HttpStatus.FORBIDDEN, Message.JWT_SIGNATURE_INVALID);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException() {
        return buildProblemDetail(HttpStatus.FORBIDDEN, Message.JWT_EXPIRED);
    }

    // General Exception Handler for other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception exception) {
        exception.printStackTrace();
        return buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, String message) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(status.value()), message);
        errorDetail.setProperty("description", message);
        return errorDetail;
    }
}
