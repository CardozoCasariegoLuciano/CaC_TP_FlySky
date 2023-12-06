package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.response.ResponseDTO;
import grupo1.FlySky.Dto.request.VuelosDTO;

import java.util.List;

public interface ICompraService {

    List<VuelosDTO> fullVuelos();

    ResponseDTO guardarVuelo(VuelosDTO vuelosDTO);
}
