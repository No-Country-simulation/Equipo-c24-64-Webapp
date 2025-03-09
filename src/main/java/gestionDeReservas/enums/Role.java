package gestionDeReservas.enums;

public enum Role {
    CUSTOMER,
    RECEPTIONIST;

    public static Role getRole(String rol) {
        return Role.valueOf(rol.toUpperCase());
    }
}