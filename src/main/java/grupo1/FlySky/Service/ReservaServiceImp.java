package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ReservaDto;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Exceptions.AsientosExcedidosException;
import grupo1.FlySky.Exceptions.DuplicateReservaException;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import grupo1.FlySky.Service.interfaces.IReservaService;
import grupo1.FlySky.Service.interfaces.IVueloService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImp implements IReservaService {
    private IReservaRepository repository;
    private ModelMapper mapper = new ModelMapper();
    private IVueloService vueloService2;

    public ReservaServiceImp(IReservaRepository repository){
        this.repository = repository;
    }

    @Override
    public ReservaDto nuevaReserva(ReservaSaveDto reserva) {

        Optional<Reserva> findedReserva = this.repository.findReservaById(reserva.getId());
        if(!findedReserva.isEmpty()){
            throw new DuplicateReservaException("Reserva ya registrada");
        }
        //VuelosDTO notFindedVuelo = vueloService.buscarPorDestino(reserva.getVueloID());
        //if(!notFindedVuelo.isEmpty()){
        //   throw new DuplicateReservaException("Reserva ya registrada");  intento buscar si existe el vuelo de la reserva
        //}

        if(vueloService2.buscarPorDestino(reserva.getVueloID()).getCuposLibres() < reserva.getCantAsientos()) {
             throw new AsientosExcedidosException("No se puede reservar mas asientos que los que hay disponibles");
        }

        //modificamos los asientos
        vueloService2.modificarAsientos(reserva.getVueloID(), reserva.getCantAsientos());

        reserva.setPrecioFinal(vueloService2.buscarPorDestino(reserva.getVueloID()).getPrecio()
               * reserva.getCantAsientos());

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
        Optional<Reserva> reservas = this.repository.findReservaByClienteID(id_usuario);

        return reservas.stream()
                .map(reserva -> this.mapper.map(reserva, ReservaDto.class))
                .collect(Collectors.toList());
    }

}
