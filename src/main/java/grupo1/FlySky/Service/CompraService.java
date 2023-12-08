package grupo1.FlySky.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import grupo1.FlySky.Dto.response.ResponseDTO;
import grupo1.FlySky.Dto.request.VueloSaveDto;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Repository.interfaces.IVueloRepository;
import grupo1.FlySky.Service.interfaces.ICompraService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService implements ICompraService {

    private IVueloRepository repository;

    public CompraService(IVueloRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VueloSaveDto> fullVuelos() {
        ObjectMapper mapper = new ObjectMapper();
        return repository.fullVuelos().stream()
                .map(vuelo -> mapper.convertValue(vuelo, VueloSaveDto.class))
                .toList();
    }

    @Override
    public ResponseDTO guardarVuelo(VueloSaveDto vuelosDTO) {
        ObjectMapper mapper = new ObjectMapper();
        Vuelo vueloEntity = mapper.convertValue(vuelosDTO, Vuelo.class);
        Vuelo resultado = repository.guardarVuelo(vueloEntity);

        if (resultado == null) {
           return new ResponseDTO("No se pudo guardar Vuelo");
        } else {
            return new ResponseDTO("Se cargo correctamente");
        }

    }
}
