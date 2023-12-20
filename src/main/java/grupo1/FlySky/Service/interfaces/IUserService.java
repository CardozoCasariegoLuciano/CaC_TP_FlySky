package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.ReservaPorUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;

import java.util.List;

public interface IUserService {
    List<UsuarioDto> listarUsuarios();

    UsuarioDto crearUsuario(CrearUsuarioDto usuario);
    ReservaPorUsuarioDto obtenerUsuarioReservas(Long id, Long usuarioId);
}
