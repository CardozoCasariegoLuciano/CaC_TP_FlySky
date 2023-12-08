package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.response.ResponseDTO;
import grupo1.FlySky.Dto.request.VueloSaveDto;

import java.util.List;

public interface ICompraService {

    List<VueloSaveDto> fullVuelos();

    ResponseDTO guardarVuelo(VueloSaveDto vuelosDTO);
}
