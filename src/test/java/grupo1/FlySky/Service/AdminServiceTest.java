package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.EstadisticasDTO;
import grupo1.FlySky.Dto.Responses.ReservaDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Exceptions.TipoDeUsuarioIncorrectoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    ReservaServiceImp reservaService;

    @InjectMocks
    AdminServiceImp service;
    @Test
    void dailyStatus_ok() {
        UsuarioDto usuarioDto = new UsuarioDto(1L, "Jose", "Josefo", LocalDate.of(1990, 5, 15),
                "test@gmail.com", "42505500", Roles.Rol.ADMINISTRADOR);
        LocalDate fecha = LocalDate.now();

        List<ReservaDto> reservaDtos = new ArrayList<>();
        reservaDtos.add(new ReservaDto(1L,2L,3L,LocalDate.of(2023,12,20),10,"mercado pago",200.00F));
        reservaDtos.add(new ReservaDto(2L,3L,4L,LocalDate.of(2023,12,20),9,"mercado pago",200.00F));

        when(reservaService.reservaPorFecha(eq(fecha))).thenReturn(reservaDtos);

        EstadisticasDTO estadisticasDTO = service.dailyStatus(usuarioDto, fecha);

        assertNotNull(estadisticasDTO);
        assertEquals(reservaDtos.size(),estadisticasDTO.getCantReservas());

        verify(reservaService, times(2)).reservaPorFecha(fecha);
    }

    @Test
    void dailyStatus_TipoDeUsuarioIncorrectoException() {
        UsuarioDto usuarioNoAdmin = new UsuarioDto();
        usuarioNoAdmin.setRol(Roles.Rol.USER);

        LocalDate fecha = LocalDate.now();

        TipoDeUsuarioIncorrectoException exception = assertThrows(TipoDeUsuarioIncorrectoException.class,
                () -> service.dailyStatus(usuarioNoAdmin, fecha));

        assertEquals("No tiene los privilegios suficientes", exception.getMessage());
        verify(reservaService, never()).reservaPorFecha(any(LocalDate.class));
    }
}
