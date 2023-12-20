package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.ReservaPorUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Exceptions.*;
import grupo1.FlySky.Repository.interfaces.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    ReservaServiceImp reservaService;
    @Mock
    IUserRepository repository;

    @InjectMocks
    UserService service;

    @Test
    void listarUsuariosTest() {
        List<Usuario> ret = List.of(new Usuario(), new Usuario());
        when(repository.findAll()).thenReturn(ret);

        List<UsuarioDto> actual = service.listarUsuarios();

        assertEquals(actual.size(), ret.size());
        assertTrue(actual.stream().allMatch(usuario -> usuario instanceof UsuarioDto));

    }

    @Test
    void crearUsuario_OK_Test() {
        CrearUsuarioDto usuario = new CrearUsuarioDto("Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500");

        Usuario ret = new Usuario(null, "Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500", Roles.Rol.USER);
        when(repository.save(any())).thenReturn(ret);
        when(repository.findUserByEmail(any())).thenReturn(Optional.empty());


        UsuarioDto actual = service.crearUsuario(usuario);
        UsuarioDto expected = new UsuarioDto(null, "Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500", Roles.Rol.USER);

        assertEquals(actual, expected);
    }

    @Test
    void crearUsuario_EmailTaken_Test() {
        CrearUsuarioDto usuario = new CrearUsuarioDto("Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500");
        when(repository.findUserByEmail(any())).thenReturn(Optional.of(new Usuario()));
        assertThrows(DuplicateUserException.class, () -> service.crearUsuario(usuario));
    }

    @Test
    void obtenerUsuarioReservas_ok() {
        Long id = 1L;
        Long usuarioId = 2L;

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setRol(Roles.Rol.AGENTE_DE_VENTAS);

        Usuario usuarioConsultado = new Usuario();
        usuarioConsultado.setId(id);

        UserService userService = new UserService(repository, reservaService);

        when(reservaService.reservasPorUsuario(anyLong())).thenReturn(new ArrayList<>());
        when(repository.findUserById(usuarioId)).thenReturn(Optional.of(usuario));
        when(repository.findUserById(id)).thenReturn(Optional.of(usuarioConsultado));

        ReservaPorUsuarioDto reservaPorUsuarioDto = userService.obtenerUsuarioReservas(id, usuarioId);

        assertNotNull(reservaPorUsuarioDto);
        assertEquals(id, reservaPorUsuarioDto.getDatosUsuario().getId());
        verify(repository, times(2)).findUserById(anyLong());
        verify(reservaService, times(1)).reservasPorUsuario(anyLong());
    }

    @Test
    void obtenerUsuarioReservas_userRol_isEmpty_return_UsuarioNotFoundException() {
        when(repository.findUserById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> service.obtenerUsuarioReservas(1L, 999L));

        verify(repository, times(1)).findUserById(999L);
    }

    @Test
    void obtenerUsuarioReservas_userRol_is_USER_return_InvalidRolException() {
        Usuario usuarioConRolIncorrecto = new Usuario();
        usuarioConRolIncorrecto.setRol(Roles.Rol.USER);

        when(repository.findUserById(anyLong())).thenReturn(Optional.of(usuarioConRolIncorrecto));

        assertThrows(InvalidRolException.class, () -> service.obtenerUsuarioReservas(1L, 999L));

        verify(repository, times(1)).findUserById(999L);
    }
}
