package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ReservaDto;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Entity.Usuario;
import grupo1.FlySky.Exceptions.DuplicateReservaException;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import grupo1.FlySky.Service.interfaces.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImp implements IReservaService {

    @Autowired
    private final IReservaRepository repository;
    private final ModelMapper mapper = new ModelMapper();
    private final VueloService vueloService;

    public ReservaServiceImp(IReservaRepository repository, VueloService vueloService){
        this.repository = repository;
        this.vueloService = vueloService;
    }

    @Override
    public ReservaDto nuevaReserva(ReservaSaveDto reserva) {

        //un paso es transformar la reserva a entidad... el tema es, completa vuelo?? No, no lo completa.

        Optional<Reserva> findedReserva = this.repository.findReservaById(reserva.getId());
        if(!findedReserva.isEmpty()){
            throw new DuplicateReservaException("Reserva ya registrada");
        }

        //modificamos los asientos
        //vueloService.modificarAsientos(reserva.getVueloID(), reserva.getCantAsientos());
        //reservaEntity.getVueloID().setCuposLibres(reservaEntity.getVueloID().getCuposLibres() - reservaEntity.getCantAsientos());
        vueloService.modificarAsientos(reserva.getVueloID(), reserva.getCantAsientos());

        //seteamos precio final
        //reservaEntity.setPrecioFinal(reservaEntity.getVueloID().getPrecio() * reservaEntity.getCantAsientos());
        reserva.setPrecioFinal(vueloService.buscarPorDestino(reserva.getVueloID()).getPrecio() * reserva.getCantAsientos());

        Reserva reservaEntity = this.mapper.map(reserva, Reserva.class);
        Reserva reservaCreada = this.repository.save(reservaEntity);

        return this.mapper.map(reservaCreada, ReservaDto.class);
    }

    @Override
    public List<ReservaDto> devolverTodos() {
        List<Reserva> reservas = this.repository.findAll();

        return reservas.stream()
                .map(reserva -> this.mapper.map(reserva, ReservaDto.class))
                .collect(Collectors.toList());
    }

    public List<ReservaDto> reservasPorUsuario (Long id_usuario){
        Usuario user = new Usuario();
        user.setId(id_usuario);
        List<Reserva> reservas = this.repository.findByClienteID(user);

        return reservas.stream()
                .map(reserva -> this.mapper.map(reserva, ReservaDto.class))
                .collect(Collectors.toList());
    }
    public List<ReservaDto> reservaPorFecha (LocalDate fecha) {
        List<Reserva> reservas = this.repository.findByFecha(fecha);

        return reservas.stream()
                .map(reserva -> this.mapper.map(reserva, ReservaDto.class))
                .collect(Collectors.toList());
    }
}
