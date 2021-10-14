package ee.mihkel.webshopbackend.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class ItemExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ItemNotFoundResponse> handleException(EmptyResultDataAccessException e) {
        ItemNotFoundResponse response = new ItemNotFoundResponse(
                new Date(),
                "ID on liiga suur või väike",
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ItemNotFoundResponse> handleException(MethodArgumentTypeMismatchException e) {
        ItemNotFoundResponse response = new ItemNotFoundResponse(
                new Date(),
                "ID asemel sisestati muu sümbol",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // samamoodi nagu ülemine Handler,
    // muutke sulgude sisene sisu --- mis exceptioni puhul ta kinni püüab
    // muutke sõnum
    // muutke httpstatus --- näiteks sobilikum Bad Request status
}
