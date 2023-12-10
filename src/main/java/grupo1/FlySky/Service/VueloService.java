package grupo1.FlySky.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import grupo1.FlySky.Dto.ResponseDTO;
import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Repository.IVueloRepository;
import grupo1.FlySky.Service.interfaces.IVueloService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VueloService implements IVueloService {

    private IVueloRepository repository;

    public VueloService(IVueloRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VuelosDTO> fullVuelos() {
        ModelMapper modelMapper = new ModelMapper();

        List<Vuelo> vuelos = repository.findAll();
        List<VuelosDTO> vuelosDTOS = new ArrayList<>();

       vuelos.forEach(v-> vuelosDTOS.add(modelMapper.map(v, VuelosDTO.class)));
       return vuelosDTOS;
    }

    @Override
    public ResponseDTO guardarVuelo(VuelosDTO vuelosDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Vuelo vueloEntity = modelMapper.map(vuelosDTO, Vuelo.class);
        Vuelo resultado = repository.save(vueloEntity);

        if (resultado == null) {
           return new ResponseDTO("No se pudo guardar Vuelo");
        } else {
            return new ResponseDTO("Se cargo correctamente");
        }
    }

    @Override
    public VuelosDTO buscarPorDestino(Long id) {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Vuelo> vuelo = repository.findById(id);

        VuelosDTO respuesta = modelMapper.map(vuelo, VuelosDTO.class);

        return respuesta;
    }
}
