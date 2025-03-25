package org.baopen753.cards.exception;

import org.baopen753.cards.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleGenericException(Exception exception, WebRequest request) {
        LOGGER.error(String.format("Exception occurred %s", exception.getMessage()));

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(request.getDescription(false));
        return errorResponseDto;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(ObjectNotFoundException exception, WebRequest request) {
        LOGGER.error(String.format("Exception occurred %s", exception.getMessage()));

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(request.getDescription(false));

        return errorResponseDto;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBadRequestException(CardIsAlreadyExistException exception, WebRequest request) {
        LOGGER.error(String.format("Exception occurred %s", exception.getMessage()));

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        errorResponseDto.setApiPath(request.getDescription(false));

        return errorResponseDto;
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errorsMessages = new HashMap<>();

        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

        validationErrors.forEach(error -> {
            String errorField = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMessages.put(errorField, errorMessage);
        });

        return new ResponseEntity<>(errorsMessages, HttpStatus.BAD_REQUEST);
    }
}
