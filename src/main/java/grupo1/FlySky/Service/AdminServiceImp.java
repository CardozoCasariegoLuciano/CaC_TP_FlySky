package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.EstadisticasDTO;
import grupo1.FlySky.Dto.Responses.ReservaDto;
import grupo1.FlySky.Dto.Responses.UsuarioDto;
import grupo1.FlySky.Entity.Roles;
import grupo1.FlySky.Exceptions.TipoDeUsuarioIncorrectoException;
import grupo1.FlySky.Service.interfaces.IAdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AdminServiceImp implements IAdminService {

    private final ModelMapper mapper = new ModelMapper();
    private final VueloService vueloService;
    private final ReservaServiceImp reservaService;


    public AdminServiceImp(VueloService vueloService, ReservaServiceImp reservaService){
        this.vueloService = vueloService;
        this.reservaService = reservaService;
    }
    @Override
    public EstadisticasDTO dailyStatus(UsuarioDto usuario, LocalDate date) {

        if (!usuario.getRol().equals(Roles.Rol.ADMINISTRADOR)) {
            throw new TipoDeUsuarioIncorrectoException("No tiene los privilegios suficientes");
        }

        ArrayList<ReservaDto> reservas = (ArrayList<ReservaDto>) reservaService.reservaPorFecha(date);
        int cantAsientos = 0;
        float sumaDiaria = 0;

        int asientosReservaMax = 0;
        Long asientosReservaMaxId = 0L;

        Long reservaMasCaraId = 0L;
        float reservaMasCara = 0F;

        for (ReservaDto reserva : reservas) {
            cantAsientos += reserva.getCantAsientos();
            sumaDiaria += reserva.getPrecioFinal();
            if (reserva.getCantAsientos() > asientosReservaMax) {
                asientosReservaMax = reserva.getCantAsientos();
                asientosReservaMaxId = reserva.getId();
            }
            if (reserva.getPrecioFinal() > reservaMasCara) {
                reservaMasCara = reserva.getPrecioFinal();
                reservaMasCaraId = reserva.getId();
            }
        }

        int cantReservas = reservaService.reservaPorFecha(date).size();

        return new EstadisticasDTO(cantAsientos,asientosReservaMax,asientosReservaMaxId,reservaMasCaraId,
                reservaMasCara,cantReservas,sumaDiaria);
    }
}