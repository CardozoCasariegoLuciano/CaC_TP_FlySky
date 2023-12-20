package grupo1.FlySky.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST, reason = "Rol invalido")
public class InvalidRolException extends RuntimeException{
}
