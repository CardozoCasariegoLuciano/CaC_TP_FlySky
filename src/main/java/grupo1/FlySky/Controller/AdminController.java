package grupo1.FlySky.Controller;

import grupo1.FlySky.Service.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService service;

    public AdminController(IAdminService service){
        this.service = service;
    }

    @GetMapping("/dailystatus/{date}")
    public ResponseEntity<?> resumenDiario(@PathVariable LocalDate date) {
        return new ResponseEntity<>(this.service.dailyStatus(date), HttpStatus.OK);
    }
}
