package grupo1.FlySky.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> DtoValidation(MethodArgumentNotValidException ex) {
        HashMap<String, String> errlist = new HashMap<>();
        ex.getFieldErrors().forEach(er -> errlist.put(er.getField(), er.getDefaultMessage()));

        return new ResponseEntity<>(errlist, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> DuplicatedUser(DuplicateUserException ex){
        HashMap<String, String> errList = new HashMap<>();
        errList.put("Duplicated", ex.getMessage());
        return new ResponseEntity<>(errList, HttpStatus.CONFLICT);
    }

}
