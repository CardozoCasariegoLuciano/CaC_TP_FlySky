package grupo1.FlySky.Controller;



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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
                "CBA", "BEA", 2,LocalDate.of(2023,12,11),LocalDate.of(2023,12,12));


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

    @DisplayName("Se pasa id y devuelve el vuelo correspondiente")
    @Test
    void buscarPorDestino_ok() {
        VuelosDTO vuelosDTO = new VuelosDTO(1L, "Latam", 2000.0, "ARG", "BRZ",
                "CBA", "BEA", 2,LocalDate.of(2023,12,11),LocalDate.of(2023,12,12));

        when(vueloService.buscarPorDestino(1L)).thenReturn(vuelosDTO);

        ResponseEntity<?> responseEntity = vueloController.buscarPorDestino(1L);

        verify(vueloService, times(1)).buscarPorDestino(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody()instanceof VuelosDTO);

        VuelosDTO respuesta = (VuelosDTO) responseEntity.getBody();

        assertEquals(vuelosDTO.getId(), respuesta.getId());
        assertEquals(vuelosDTO.getAerolinea(), respuesta.getAerolinea());
        assertEquals(vuelosDTO.getPrecio(), respuesta.getPrecio());
        assertEquals(vuelosDTO.getPaisOrigen(), respuesta.getPaisOrigen());
        assertEquals(vuelosDTO.getPaisDestino(), respuesta.getPaisDestino());
        assertEquals(vuelosDTO.getAeropuertoOrigen(), respuesta.getAeropuertoOrigen());
        assertEquals(vuelosDTO.getAeropuertoDestino(), respuesta.getAeropuertoDestino());
        assertEquals(vuelosDTO.getCuposLibres(), respuesta.getCuposLibres());
    }

    @DisplayName("Se pasa parametro null")
    @Test
    void buscarPorDestino_null() {
        when(vueloService.buscarPorDestino(1L)).thenReturn(null);

        ResponseEntity<?> responseEntity = vueloController.buscarPorDestino(1L);

        verify(vueloService, times(1)).buscarPorDestino(1L);
        assertNull(responseEntity.getBody());
    }

    @DisplayName("Devuelve lista de vuelos")
    @Test
    void fullVuelos_ok() {
        List<VuelosDTO> vuelosDTOS = Arrays.asList(
                new VuelosDTO(1L, "Latam", 2000.0, "ARG", "BRZ", "CBA", "BEA", 2,LocalDate.of(2023,12,11),LocalDate.of(2023,12,12)),
                new VuelosDTO(2L, "Aerolinea2", 2500.0, "USA", "CAN", "NYC", "TOR", 1,LocalDate.of(2023,12,11),LocalDate.of(2023,12,14))
        );

        when(vueloService.fullVuelos()).thenReturn(vuelosDTOS);
        ResponseEntity<?> responseEntity = vueloController.fullVuelos();

        verify(vueloService, times(1)).fullVuelos();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof List);

        List<VuelosDTO> responseList = (List<VuelosDTO>) responseEntity.getBody();
        assertEquals(vuelosDTOS.size(), responseList.size());
    }
}
