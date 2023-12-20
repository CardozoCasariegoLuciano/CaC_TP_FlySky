package grupo1.FlySky.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "no se encontro ese rol/usuario")
public class UsuarioNotFoundException extends RuntimeException{
}
