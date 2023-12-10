package grupo1.FlySky.Controller;


import grupo1.FlySky.Dto.ResponseDTO;
import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Service.VueloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
 class VueloControllerTest {

    @Mock
    private VueloService vueloService;


    @InjectMocks
    private VueloController vueloController;

    @Test
    @DisplayName("Se pasan los parametros y guardavuelos persiste los datos")
     void guardarVuelo_ok() {

        VuelosDTO vuelosDTO = new VuelosDTO(1L, "Latam", 2000.0, "ARG", "BRZ",
                "CBA", "BEA", 2);


        ResponseEntity<?> responseEntity = vueloController.guardarVuelo(vuelosDTO);

        verify(vueloService, times(1)).guardarVuelo(vuelosDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @DisplayName("Se pasan los parametros null y se valida excepcion")
    @Test
     void guardarVuelo_parametro_null() {

        VuelosDTO vuelosDTO = new VuelosDTO();

        doThrow(RuntimeException.class).when(vueloService).guardarVuelo(vuelosDTO);

        assertThrows(RuntimeException.class, () -> vueloController.guardarVuelo(null));
    }


}
