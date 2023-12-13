package grupo1.FlySky.Service.interfaces;


import grupo1.FlySky.Dto.Responses.ResponseDTO;
import grupo1.FlySky.Dto.VuelosDTO;

import java.util.List;

public interface IVueloService {

    List<VuelosDTO> fullVuelos();

     ResponseDTO guardarVuelo(VuelosDTO vuelosDTO);

     VuelosDTO buscarPorDestino(Long id);
}
