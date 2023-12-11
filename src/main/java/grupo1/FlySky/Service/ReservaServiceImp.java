package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ReservaDto;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Exceptions.DuplicateReservaException;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import grupo1.FlySky.Service.interfaces.IReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImp implements IReservaService {
    private IReservaRepository repository;
    private ModelMapper mapper = new ModelMapper();

    public ReservaServiceImp(IReservaRepository repository){
        this.repository = repository;
    }

    @Override
    public ReservaDto nuevaReserva(ReservaSaveDto reserva) {
        VueloServiceImp vueloService = new VueloServiceImp();
        Optional<Reserva> findedReserva = this.repository.findReservaById(reserva.getId());
        if(!findedReserva.isEmpty()){
            throw new DuplicateReservaException("Reserva ya registrada");
        }
        //if(!vueloService.findVueloById(reserva.getVueloID).getAsientosLibres > reserva.getCantAsientos()){
        //     throw new AsientosExcedidosException("No se puede reservas mas asientos que los que hay disponibles");
        //}

       // reserva.setPrecioFinal(vueloService.findVueloById(reserva.getVueloID())
       //         .getPrecio*reserva.getCantAsientos()); //seteo el precio buscando en vuelo
        Reserva reservaEntity = this.mapper.map(reserva, Reserva.class);

        Reserva reservaCreada = this.repository.save(reservaEntity);

        //
        // le saco asientos libres al vuelo
        return this.mapper.map(reservaCreada, ReservaDto.class);

        //
    }

    @Override
    public List<ReservaDto> devolverTodos() {
        List<Reserva> reservas = this.repository.findAll();

        return reservas.stream()
                .map(reserva -> this.mapper.map(reserva, ReservaDto.class))
                .collect(Collectors.toList());
    }
}
