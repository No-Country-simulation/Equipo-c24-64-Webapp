package gestionDeReservas.model.enums;

public enum Role {
    CUSTOMER,
    RECEPTIONIST;

    public static Role getRole(String rol) {
        return Role.valueOf(rol.toUpperCase());
    }
}