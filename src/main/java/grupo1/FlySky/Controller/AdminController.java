package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Service.interfaces.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService service;

    public AdminController(IAdminService service){
        this.service = service;
    }

    @GetMapping("/dailystatus/{date}")
    public ResponseEntity<?> resumenDiario(@RequestBody UsuarioDto body, LocalDate date) {
        return new ResponseEntity<>(this.service.dailyStatus(body,date), HttpStatus.OK);
    }
}
