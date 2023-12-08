package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController controller;

    @Test
    void listarUsuariosTest(){
       List<UsuarioDto> resp = List.of(new UsuarioDto());
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.OK);
        when(service.listarUsuarios()).thenReturn(resp);

        ResponseEntity<?> actual = controller.listarUsuarios();

        assertEquals(expected, actual);
    }
}
