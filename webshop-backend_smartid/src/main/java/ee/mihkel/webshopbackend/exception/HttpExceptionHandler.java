package ee.mihkel.webshopbackend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(EmptyResultDataAccessException e) {
        HttpExceptionResponse response = new HttpExceptionResponse(
                new Date(),
                "Eseme ID on liiga suur või väike",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(MethodArgumentTypeMismatchException e) {
        HttpExceptionResponse response = new HttpExceptionResponse(
                new Date(),
                "Eseme ID asemel sisestati muu sümbol",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(ItemNotFoundException e) {
        HttpExceptionResponse response = new HttpExceptionResponse(
                new Date(),
                "Eseme ID on liiga suur või väike",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(UserAlreadyExistsException e) {
        HttpExceptionResponse response = new HttpExceptionResponse(
                new Date(),
                "Kasutaja on juba registreerunud",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(TransactionSystemException e) {
        HttpExceptionResponse response = new HttpExceptionResponse(
                new Date(),
                "Kasutaja kohustuslikud väljad on täitmata",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(NoSuchElementException e) { // errori nime saan konsoolist
        HttpExceptionResponse response = new HttpExceptionResponse( // seda vastust v]ib kasutada ka siin
                new Date(),
                "Sellist kasutajat ei eksisteeri.",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpExceptionResponse> handleException(DataIntegrityViolationException e) { // errori nime saan konsoolist
        HttpExceptionResponse response = new HttpExceptionResponse( // seda vastust v]ib kasutada ka siin
                new Date(),
                "Sellist kasutajat ei eksisteeri.",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
