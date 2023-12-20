package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.ReservaPorUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Exceptions.DuplicateUserException;
import grupo1.FlySky.Exceptions.InvalidRolException;
import grupo1.FlySky.Exceptions.UsuarioNotFoundException;
import grupo1.FlySky.Repository.interfaces.IUserRepository;
import grupo1.FlySky.Service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    ReservaServiceImp reservaService;
    IUserRepository repository;
    ModelMapper maper = new ModelMapper();

    public UserService(IUserRepository repository, ReservaServiceImp reservaService) {
        this.repository = repository;
        this.reservaService = reservaService;
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
    @Override
    public ReservaPorUsuarioDto obtenerUsuarioReservas(Long id, Long usuarioId) {

        Optional<Usuario> userRol = this.repository.findUserById(usuarioId);
        Optional<Usuario> userRol2 = this.repository.findUserById(id);
        if(userRol.isEmpty()){
            throw new UsuarioNotFoundException();//hacer handler en excep controller
        }
        if(userRol.get().getRol()!=Roles.Rol.AGENTE_DE_VENTAS){
            throw new InvalidRolException(); // no tenes el rol adecuado + hacer handler en excep controller
        }
        //logica propia del metodo....
        ReservaPorUsuarioDto usuarioConsultado = new ReservaPorUsuarioDto();
        UsuarioDto usuarioDatos = this.maper.map(userRol2,UsuarioDto.class);
        usuarioConsultado.setDatosUsuario(usuarioDatos);;
        usuarioConsultado.setReservasRealizadas(reservaService.reservasPorUsuario(id));
        return  usuarioConsultado;

    }

}
