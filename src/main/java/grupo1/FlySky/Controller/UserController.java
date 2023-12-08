package grupo1.FlySky.Controller;

import grupo1.FlySky.Service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}
