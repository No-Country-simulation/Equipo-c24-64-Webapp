class User {
  constructor(nombre, apellido, email, telefono, habitacionId) {
    this.id = Date.now().toString();
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.telefono = telefono;
    this.habitacionId = habitacionId;
  }
}

export default User;
