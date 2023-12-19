package grupo1.FlySky.Controller;

import grupo1.FlySky.Service.AdminServiceImp;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    AdminServiceImp service;

    @InjectMocks
    AdminController controller;
/*
    @Test
    void resumenDiarioTest() {
        /* EstadisticasDTO resp = new EstadisticasDTO();
        ResponseEntity<?> expected = new ResponseEntity<>(resp, HttpStatus.OK);
        when(service.dailyStatus(new UsuarioDto()
                , LocalDate.of()));

        ResponseEntity<?> actual = controller.resumenDiario();

        assertEquals(expected, actual);
    }
*/

}
