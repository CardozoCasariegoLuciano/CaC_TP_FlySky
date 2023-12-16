package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Exceptions.DuplicateUserException;
import grupo1.FlySky.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    IUserRepository repository;
    ModelMapper maper = new ModelMapper();

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = this.repository.findAll();

        return usuarios.stream()
                .map(user -> this.maper.map(user, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto crearUsuario(CrearUsuarioDto user) {
        Optional<Usuario> findedUser = this.repository.findUserByEmail(user.getEmail());
        if (!findedUser.isEmpty()) {
            throw new DuplicateUserException("Email already taken");
        }

        Usuario usuario = this.maper.map(user, Usuario.class);
        usuario.setRol(Roles.Rol.USER);

        Usuario usuarioCreado = this.repository.save(usuario);
        return this.maper.map(usuarioCreado, UsuarioDto.class);
    }

}
