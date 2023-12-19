package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.Requests.ReservaSaveDto;
import grupo1.FlySky.Dto.Responses.ReservaDto;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Exceptions.DuplicateReservaException;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        ReservaSaveDto reserva = new ReservaSaveDto(null, null, null,LocalDate.of(2015,5,5), 5, "mercadoPago",5000.00);

        Vuelo vuelo = new Vuelo(1L,"AA",5000D,"ARG","ARG","EZE","MDQ",60, LocalDateTime.of(2015,5,5,15,30), LocalDateTime.of(2015,5,6,15,30));
        Usuario usuario = new Usuario(1L,"Ale","Gonzalez",LocalDate.of(1999,3,5),"alemunter99@gmail.com","1136XXXXXX", Roles.Rol.USER);
        Reserva ret = new Reserva(null, vuelo, usuario, LocalDate.of(5,5,5), 5000.00F, 5,"mercadoPago");
        when(repository.save(any())).thenReturn(ret);
        when(repository.findReservaById(any())).thenReturn(Optional.empty());

        ReservaDto actual = service.nuevaReserva(reserva);
        ReservaDto expected = new ReservaDto(null, null, null,LocalDate.of(2015,5,5), 5, "mercadoPago",5000.00F);

        assertEquals(actual, expected);
    }

    @Test
    void crearReserva_IdExistente_Test() {
        ReservaSaveDto reserva = new ReservaSaveDto(1L, 1L, 5L,LocalDate.of(5,5,5), 5, "mercadoPago",5000.00F);
        when(repository.findReservaById(any())).thenReturn(Optional.of(new Reserva()));
        assertThrows(DuplicateReservaException.class, () -> service.nuevaReserva(reserva));
    }
}
