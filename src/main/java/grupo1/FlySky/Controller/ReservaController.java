package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Service.ReservaServiceImp;
import grupo1.FlySky.Service.interfaces.IReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
public class ReservaController {

    private IReservaService service;

    public ReservaController(ReservaServiceImp service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> devolverTodos() {
        return new ResponseEntity<>(service.devolverTodos(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> nuevaReserva(@RequestBody ReservaSaveDto reserva) {
        return new ResponseEntity<>(service.nuevaReserva(reserva), HttpStatus.CREATED);
    }

}