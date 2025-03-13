package gestionDeReservas.enums;

public enum Role {
    CUSTOMER,
    ADMIN;

    public static Role getRole(String rol) {
        return Role.valueOf(rol.toUpperCase());
    }
}