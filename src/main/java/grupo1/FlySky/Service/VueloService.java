package grupo1.FlySky.Service;


import grupo1.FlySky.Dto.Responses.ResponseDTO;
import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Exceptions.AsientosExcedidosException;
import grupo1.FlySky.Repository.interfaces.IVueloRepository;
import grupo1.FlySky.Service.interfaces.IVueloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VueloService implements IVueloService {

    private final IVueloRepository repository;


    @Autowired
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

    @Override
    public void modificarAsientos(Long id, int cantAsientos){
        ModelMapper modelMapper = new ModelMapper();

        Optional<Vuelo> vuelo = this.repository.findById(id);
        Vuelo vueloEntity = modelMapper.map(vuelo, Vuelo.class);

        if (vueloEntity.getCuposLibres() < cantAsientos) {
            throw new AsientosExcedidosException("No se puede reservar mas asientos que los que hay disponibles");
        }
        vueloEntity.setCuposLibres(vueloEntity.getCuposLibres()-cantAsientos);
        this.repository.save(vueloEntity);
    }
}
