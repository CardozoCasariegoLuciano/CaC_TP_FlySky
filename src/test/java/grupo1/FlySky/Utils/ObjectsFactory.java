package grupo1.FlySky.Utils;

import grupo1.FlySky.Dto.VuelosDTO;

public class ObjectsFactory {
public static VuelosDTO VueloDTOUno(){
    return new VuelosDTO(1L,"British Airways",900.00,"Argentina","Italia","Ezeiza","Malpensa",9 );
}
    public static VuelosDTO VueloDTODos(){
        return new VuelosDTO(2L,"Aerolineas Argentinas",800.00,"Argentina","Israel","Ezeiza","Ben Gurión",44 );
    }
}
