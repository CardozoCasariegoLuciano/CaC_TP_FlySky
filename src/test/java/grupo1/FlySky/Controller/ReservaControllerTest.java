package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.Requests.ReservaSaveDto;
import grupo1.FlySky.Dto.Responses.ReservaDto;
import grupo1.FlySky.Service.ReservaServiceImp;
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
        List<ReservaDto> resp = List.of(new ReservaDto());
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.OK);
        when(service.devolverTodos()).thenReturn(resp);

        ResponseEntity<?> actual = controller.devolverTodos();

        assertEquals(expected, actual);
    }

    @Test
    void crearReservaTest() {
        ReservaSaveDto reserva = new ReservaSaveDto((long) 1, (long)1, (long) 5, LocalDate.of(5,5,5), 5, "mercadoPago",5000.00);

        ReservaDto resp = new ReservaDto();
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.CREATED);

        when(service.nuevaReserva(any())).thenReturn(resp);

        ResponseEntity<?> actual = controller.nuevaReserva(reserva);

        assertEquals(expected, actual);
    }
}
