package grupo1.FlySky.Controller;


import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Service.interfaces.IVueloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VueloController {

    private IVueloService vueloService;

    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping("/fullVuelos")
    public ResponseEntity<?> fullVuelos() {
        return new ResponseEntity<>(vueloService.fullVuelos(), HttpStatus.OK);
    }

    @PostMapping("/guardarVuelos")
    public ResponseEntity<?> guardarVuelo(@RequestBody VuelosDTO vuelosDTO) {
        return new ResponseEntity<>(vueloService.guardarVuelo(vuelosDTO), HttpStatus.OK);
    }

    @GetMapping("/filtar/{id}")
    public ResponseEntity<?> buscarPorDestino(@RequestParam Long id) {
        return new ResponseEntity<>(vueloService.buscarPorDestino(id), HttpStatus.OK);
    }

}
