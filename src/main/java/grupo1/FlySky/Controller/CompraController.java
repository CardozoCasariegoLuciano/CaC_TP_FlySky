package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Service.interfaces.ICompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

    private ICompraService service;

    public CompraController(ICompraService service) {
        this.service = service;
    }

    @GetMapping("/fullVuelos")
    public ResponseEntity<?> fullVuelos() {
        return new ResponseEntity<>(service.fullVuelos(), HttpStatus.OK);
    }

    @PostMapping("/guardarVuelos")
    public ResponseEntity<?> guardarVuelo(@RequestBody VuelosDTO vuelosDTO) {
        return new ResponseEntity<>(service.guardarVuelo(vuelosDTO), HttpStatus.OK);
    }

}
