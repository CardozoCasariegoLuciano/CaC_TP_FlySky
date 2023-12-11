package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Requests.CrearUsuarioDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ReservaDto;
import grupo1.FlySky.Service.ReservaServiceImp;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaControllerTest {

    @Mock
    ReservaServiceImp service;

    @InjectMocks
    ReservaController controller;

    @Test
    void listarReservasTest() {
        List<ReservaDto> resp = List.of(new ResevaDto());
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.OK);
        when(service.devolverTodos()).thenReturn(resp);

        ResponseEntity<?> actual = controller.devolverTodos();

        assertEquals(expected, actual);
    }

    @Test
    void crearReservaTest() { //falta modificar
        ReservaSaveDto reserva = new ReservaSaveDto(1,1,LocalDate.of(1,1,2023),5,"mercadoPago",5000.00);

        ReservaDto resp = new ReservaDto();
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.CREATED);

        when(service.nuevaReserva(any())).thenReturn(resp);

        ResponseEntity<?> actual = controller.nuevaReserva(reserva);

        assertEquals(expected, actual);
    }
}
