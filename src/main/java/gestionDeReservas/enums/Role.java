package gestionDeReservas.enums;

public enum Role {
    CUSTOMER,
    RECEPCIONIST;

    public static Role getRole(String rol) {
        return Role.valueOf(rol.toUpperCase());
    }
}