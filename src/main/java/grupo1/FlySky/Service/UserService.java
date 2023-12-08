package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
