package grupo1.FlySky.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Roles {

    public enum Rol {
        USER,
        AGENTE_DE_VENTAS,
        ADMINISTRADOR,

    }
}
