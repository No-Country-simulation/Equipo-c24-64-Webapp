class User {
  constructor(
    nombre,
    apellido,
    email,
    telefono,
    habitacionId,
    dni,
    estado,
    rol
  ) {
    this.id = Date.now().toString();
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.telefono = telefono;
    this.habitacionId = habitacionId;
    this.dni = dni;
    this.estado = estado;
    this.rol = rol;
  }
}

export default User;
