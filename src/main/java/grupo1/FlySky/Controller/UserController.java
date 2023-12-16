package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> listarUsuarios() {
        return new ResponseEntity<>(this.service.listarUsuarios(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> crearUsuario(@RequestBody @Valid  CrearUsuarioDto body) {
        return new ResponseEntity<>(this.service.crearUsuario(body), HttpStatus.CREATED);
    }
}
