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

    @ExceptionHandler(DuplicateReservaException.class)
    public ResponseEntity<?> DuplicatedReserva(DuplicateReservaException ex){
        HashMap<String, String> errList = new HashMap<>();
        errList.put("Duplicated", ex.getMessage());
        return new ResponseEntity<>(errList, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AsientosExcedidosException.class)
    public ResponseEntity<?> NotAvailableSeats(AsientosExcedidosException ex){
        HashMap<String, String> errList = new HashMap<>();
        errList.put("Not available seats", ex.getMessage());
        return new ResponseEntity<>(errList, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TipoDeUsuarioIncorrectoException.class)
    public ResponseEntity<?> NotMeetsPrivilegies(TipoDeUsuarioIncorrectoException ex){
        HashMap<String, String> errList = new HashMap<>();
        errList.put("Not meeting priviligies", ex.getMessage());
        return new ResponseEntity<>(errList, HttpStatus.UNAUTHORIZED);
    }

}
