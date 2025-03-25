package org.baopen753.loans.exception;

import org.baopen753.loans.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleGenericException(Exception ex, WebRequest request) {
        LOGGER.error("Exception occurred: HTTP - {} {}", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setApiPath(request.getDescription(false));

        return errorResponse;
    }


    @ExceptionHandler(LoanAccountAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleLoanAccountIsAlreadyExistException(LoanAccountAlreadyExistException exception, WebRequest request) {
        LOGGER.error("Exception occurred: HTTP - {} {}", HttpStatus.BAD_REQUEST, exception.getMessage());

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setApiPath(request.getDescription(false));

        return errorResponse;
    }


    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleObjectNotFoundException(ObjectNotFoundException exception, WebRequest request) {
        LOGGER.error("Exception occurred: HTTP - {} {}", HttpStatus.NOT_FOUND, exception.getMessage());

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponse.setErrorMessage(exception.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setApiPath(request.getDescription(false));

        return errorResponse;
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> validationErrors = new HashMap<>();

        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();

        objectErrorList.forEach(error -> {
            String errorCode = error.getCode();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(errorCode, errorMessage);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // Extract all validation error messages
        List<String> errors = ex.getParameterValidationResults().stream()
                .flatMap(result -> result.getResolvableErrors().stream())
                .map(error -> error.getDefaultMessage())  // Get validation messages
                .collect(Collectors.toList());

        // Create a structured error response
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorMessage(errors.toString());  // Include all validation errors
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setApiPath(request.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
