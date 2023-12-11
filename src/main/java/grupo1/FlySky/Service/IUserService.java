package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;

import java.util.List;

public interface IUserService {
    List<UsuarioDto> listarUsuarios();

    UsuarioDto crearUsuario(CrearUsuarioDto usuario);
}
