package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Exceptions.DuplicateUserException;
import grupo1.FlySky.Repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

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
        try {
            CrearUsuarioDto usuario = new CrearUsuarioDto("Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500");
            when(repository.findUserByEmail(any())).thenReturn(Optional.of(new Usuario()));

            service.crearUsuario(usuario);
            fail("Se esperaba DuplicateUserException pero no se lanzó.");
        } catch (DuplicateUserException e) {
            assertNotNull(e.getMessage());
            assertTrue(e instanceof DuplicateUserException);
        }
    }
}
