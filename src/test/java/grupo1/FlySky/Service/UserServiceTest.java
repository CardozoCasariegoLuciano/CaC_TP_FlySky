package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    IUserRepository repository;

    @InjectMocks
    UserService service;

    @Test
    void listarUsuariosTest(){
        List<Usuario> ret = List.of(new Usuario(), new Usuario());
        when(repository.findAll()).thenReturn(ret);

        List<UsuarioDto> actual = service.listarUsuarios();

        assertEquals(actual.size(), ret.size());
        assertTrue(actual.stream().allMatch(usuario -> usuario instanceof UsuarioDto));

    }
}
