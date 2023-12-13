package grupo1.FlySky.Service;


import grupo1.FlySky.Dto.Responses.ResponseDTO;
import grupo1.FlySky.Dto.VuelosDTO;
import grupo1.FlySky.Entity.Vuelo;
import grupo1.FlySky.Repository.IVueloRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VueloServiceTest {

    @Mock
    private IVueloRepository repository;

    @InjectMocks
    private VueloService service;

    @Test
    @Description("Devuelve lista de vuelos")
    void fullVuelos_ok() {
        Vuelo vuelo1 = new Vuelo(1L, "Latam", 2000.0, "ARG", "BRZ", "CBA", "BEA", 2,LocalDate.of(2023,12,11),LocalDate.of(2023,12,12));
        Vuelo vuelo2 = new Vuelo(2L, "Aerolinea2", 2500.0, "USA", "CAN", "NYC", "TOR", 1,LocalDate.of(2023,12,11),LocalDate.of(2023,12,14));

        List<Vuelo> vuelosDTOS = List.of(vuelo1,vuelo2);

        when(repository.findAll()).thenReturn(vuelosDTOS);

        List<VuelosDTO> respuesta = service.fullVuelos();

        assertEquals(2, respuesta.size());
    }

    @Test
    @Description("Se pasa una lista vacia")
    void fullVuelos_vacio() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<VuelosDTO> respuesta = service.fullVuelos();

        assertEquals(0, respuesta.size());
    }

    @Test
    @Description("Se pasan los parametros correctos")
    void guardarVuelo_ok() {
        VuelosDTO vuelosDTO = new VuelosDTO(1L, "Latam", 2000.0, "ARG", "BRZ",
                "CBA", "BEA", 2, LocalDate.of(2023,12,11),LocalDate.of(2023,12,12));

        when(repository.save(any(Vuelo.class))).thenReturn(new Vuelo());

        ResponseDTO responseDTO = service.guardarVuelo(vuelosDTO);

        assertEquals("Se cargo correctamente", responseDTO.getMensaje());
    }

    @Test
    @Description("Se response null")
    void guardarVuelo_error() {
        VuelosDTO vuelosDTO = new VuelosDTO();

        when(repository.save(any(Vuelo.class))).thenReturn(null);

        ResponseDTO responseDTO = service.guardarVuelo(vuelosDTO);

        assertEquals("No se pudo guardar Vuelo", responseDTO.getMensaje());
    }

}
