package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ReservaDto;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Exceptions.DuplicateReservaException;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    IReservaRepository repository;

    @InjectMocks
    ReservaServiceImp service;

    @Test
    void listarReservasTest() {
        List<Reserva> ret = List.of(new Reserva(), new Reserva());
        when(repository.findAll()).thenReturn(ret);

        List<ReservaDto> actual = service.devolverTodos();

        assertEquals(actual.size(), ret.size());
        assertTrue(actual.stream().allMatch(reserva -> reserva instanceof ReservaDto));

    }

    @Test
    void crearReserva_OK_Test() {
        ReservaSaveDto reserva = new ReservaSaveDto(null, null, null, 5, "mercadoPago",5000.00);

        Vuelo vuelo = new Vuelo();
        Usuario usuario = new Usuario();
        Reserva ret = new Reserva(null, vuelo, usuario, 5000.00, 5,"mercadoPago");
        when(repository.save(any())).thenReturn(ret);
        when(repository.findReservaById(any())).thenReturn(Optional.empty());

        ReservaDto actual = service.nuevaReserva(reserva);
        ReservaDto expected = new ReservaDto(null, null, null, 5, "mercadoPago", 5000.00);

        assertEquals(actual, expected);
    }

    @Test
    void crearReserva_IdExistente_Test() {
        ReservaSaveDto reserva = new ReservaSaveDto((long) 1, (long)1, (long) 5, 5, "mercadoPago",5000.00);
        when(repository.findReservaById(any())).thenReturn(Optional.of(new Reserva()));
        assertThrows(DuplicateReservaException.class, () -> service.nuevaReserva(reserva));
    }
}
