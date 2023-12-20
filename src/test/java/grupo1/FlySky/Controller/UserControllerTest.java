package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.ReservaPorUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserService service;

    @InjectMocks
    UserController controller;

    @Test
    void listarUsuariosTest() {
        List<UsuarioDto> resp = List.of(new UsuarioDto());
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.OK);
        when(service.listarUsuarios()).thenReturn(resp);

        ResponseEntity<?> actual = controller.listarUsuarios();

        assertEquals(expected, actual);
    }

    @Test
    void crearUsuarioTest() {
        CrearUsuarioDto usuario = new CrearUsuarioDto("Usuario", "Perez", LocalDate.of(1990, 5, 15), "test@gmail.com", "42505500");

        UsuarioDto resp = new UsuarioDto();
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.CREATED);

        when(service.crearUsuario(any())).thenReturn(resp);

        ResponseEntity<?> actual = controller.crearUsuario(usuario);

        assertEquals(expected, actual);
    }

    @Test
    void obtenerPorIdTest() {
        Long id = 1L;
        Long usuarioId = 2L;

        ReservaPorUsuarioDto reservaPorUsuarioDto = mock(ReservaPorUsuarioDto.class);
        when(service.obtenerUsuarioReservas(id,usuarioId)).thenReturn(reservaPorUsuarioDto);

        ResponseEntity<?> respuesta = controller.obtenerPorId(id, usuarioId);

        verify(service, times(1)).obtenerUsuarioReservas(id, usuarioId);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }

}
