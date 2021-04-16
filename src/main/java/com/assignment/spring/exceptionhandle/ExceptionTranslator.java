package com.assignment.spring.exceptionhandle;

import com.assignment.spring.api.WeatherForecastException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ExceptionTranslator.class);

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.error("Missing Request Parameter error. For request {}.", request, ex);
        return new ErrorResponseBuilder()
                .errorKey("MISSING_REQUEST_PARAM")
                .message("Query parameter not found in URL")
                .buildResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(HttpServletRequest request, Exception ex) {

        logger.error("Internal Server error. For request {}.", request, ex);
        return new ErrorResponseBuilder()
                .errorKey("INTERNAL_SERVER_ERROR")
                .message("An unexpected exception occurred. Please try again later!!")
                .buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WeatherForecastException.class)
    public ResponseEntity<Object> handleWeatherForecastException(HttpServletRequest request, WeatherForecastException ex) {

        logger.error("Unable to retrieve weather (Empty Body). For request {}.", request, ex);
        return new ErrorResponseBuilder()
                .errorKey("NO_WEATHER_FORECAST_ERROR")
                .message("Unable to retrieve weather for given search criteria.")
                .buildResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Object> handleHttpStatusCodeException(HttpServletRequest request, HttpStatusCodeException ex) {

        logger.error("Unable to retrieve weather. For request {}.", request, ex);
        return new ErrorResponseBuilder()
                .errorKey("WEATHER_FORECAST_ERROR")
                .message(String.format("Unable to fulfill your request. Error: %s", ex.getMessage()))
                .buildResponseEntity(ex.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {

        logger.error("Invalid request {}.", request, ex);

        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();

        return new ErrorResponseBuilder()
                .errorKey("INVALID_REQUEST")
                .message(String.format("Invalid request. Field errors %s", fieldErrors))
                .buildResponseEntity(HttpStatus.BAD_REQUEST);
    }
}