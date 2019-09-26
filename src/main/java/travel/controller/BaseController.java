package travel.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import travel.model.response.DataResponse;
import travel.util.ErrorCode;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Validated
public class BaseController  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String code = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String message = ErrorCode.getMessage(code);

        DataResponse errResponse = new DataResponse();
        errResponse.setCode(code);
        errResponse.setMessage(message);

        return ResponseEntity.ok(errResponse);

    }

}
