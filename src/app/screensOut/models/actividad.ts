export class Actividad {
  id: number;
  nombre: string;
  fechaCreacion: Date;
  fechaVencimiento: Date
  fechaTermino: Date;
  calificacion: number;
  status: boolean;

  constructor(id: number, nombre: string, fechaCreacion: Date, fechaVencimiento: Date, fechaTermino: Date, calificacion: number, status: boolean) {
    this.id = id;
    this.nombre = nombre;
    this.fechaCreacion = fechaCreacion;
    this.fechaVencimiento = fechaVencimiento;
    this.fechaTermino = fechaTermino;
    this.calificacion = calificacion;
    this.status = status;
  }
}
