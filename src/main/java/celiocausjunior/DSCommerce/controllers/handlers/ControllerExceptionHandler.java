package celiocausjunior.DSCommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import celiocausjunior.DSCommerce.models.dtos.CustomErrorDTO;
import celiocausjunior.DSCommerce.models.dtos.ValidationErrorDTO;
import celiocausjunior.DSCommerce.services.exceptions.DataIntegrityException;
import celiocausjunior.DSCommerce.services.exceptions.ForbiddenException;
import celiocausjunior.DSCommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound (ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(),  e.getMessage(), request.getRequestURI());  
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<CustomErrorDTO> dataIntegrityException (DataIntegrityException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(),  e.getMessage(), request.getRequestURI());  
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid (MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDTO error = new ValidationErrorDTO(Instant.now(), status.value(),  "Dados inválidos", request.getRequestURI());  
        
      for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        
        return ResponseEntity.status(status).body(error);
    }

        @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<CustomErrorDTO> forbiddenException (ForbiddenException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.FORBIDDEN;
        CustomErrorDTO error = new CustomErrorDTO(Instant.now(), status.value(),  e.getMessage(), request.getRequestURI());  
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
