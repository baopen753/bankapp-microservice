package org.baopen753.accounts.exception;

import org.baopen753.accounts.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDto handleGenericException(WebRequest request, Exception exception) {
        LOGGER.info("Log message: {HttpStatus.INTERNAL_SERVER_ERROR} " + exception.getMessage());
        ErrorResponseDto errorDto = new ErrorResponseDto();

        errorDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDto.setErrorMessage(exception.getMessage());
        errorDto.setApiPath(request.getDescription(false));
        errorDto.setTimestamp(LocalDateTime.now());

        return errorDto;
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleCustomerAlreadyExistException(WebRequest request, CustomerAlreadyExistException exception) {
        LOGGER.info("Log message: {HttpStatus.BAD_REQUEST} " + exception.getMessage());
        ErrorResponseDto errorDto = new ErrorResponseDto();

        errorDto.setErrorCode(HttpStatus.BAD_REQUEST);
        errorDto.setErrorMessage(exception.getMessage());
        errorDto.setApiPath(request.getDescription(false));
        errorDto.setTimestamp(LocalDateTime.now());

        return errorDto;
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleObjectNotFoundException(WebRequest request, ObjectNotFoundException exception) {
        LOGGER.info("Log message: {HttpStatus.NOT_FOUND} " + exception.getMessage());
        ErrorResponseDto errorDto = new ErrorResponseDto();

        errorDto.setErrorCode(HttpStatus.NOT_FOUND);
        errorDto.setErrorMessage(exception.getMessage());
        errorDto.setApiPath(request.getDescription(false));
        errorDto.setTimestamp(LocalDateTime.now());

        return errorDto;
    }


    // this exception is invoked when sending invalid body via request with @RequestBody
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();

        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach(error -> {                     // error - ObjectError
            String errorField = ((FieldError) error).getField();   // FieldError extends ObjectError
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(errorField, errorMessage);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
