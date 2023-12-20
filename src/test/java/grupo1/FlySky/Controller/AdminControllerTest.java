package grupo1.FlySky.Controller;

import grupo1.FlySky.Dto.EstadisticasDTO;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Service.AdminServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    AdminServiceImp service;

    @InjectMocks
    AdminController controller;

    @Test
    void resumenDiarioTest() {
        UsuarioDto usuarioDto = mock(UsuarioDto.class);
        LocalDate fecha = LocalDate.now();
        EstadisticasDTO resp = new EstadisticasDTO();

        when(service.dailyStatus(usuarioDto,fecha)).thenReturn(resp);

        ResponseEntity<?> response = controller.resumenDiario(usuarioDto,fecha);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}