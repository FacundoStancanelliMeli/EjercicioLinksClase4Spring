package com.example.links.resthandlerexceptions;

import com.example.links.dtos.ErrorDTO;
import com.example.links.exceptions.LinkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestHandler {

    @ExceptionHandler(LinkException.class)
    public ResponseEntity handleException(LinkException ex)
    {
        ErrorDTO errorDTO = new ErrorDTO(ex.getCode(), ex.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (errorDTO.getTitulo())
        {
            case LinkException.URL_NOT_VALID:
            case LinkException.LINK_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;

            case LinkException.ID_ALREADY_EXISTS:
                status = HttpStatus.CONFLICT;
                break;

            case LinkException.PASSWORD_INVALID:
                status = HttpStatus.UNAUTHORIZED;
                break;
        }

        ResponseEntity response = new ResponseEntity(errorDTO, status);

        return response;
    }
}
