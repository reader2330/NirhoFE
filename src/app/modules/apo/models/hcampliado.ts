export class HCAmpliado {
  idParticipante: string;
  objetivoPuesto: string;
  funcionPuesto: string;
  actividadPuesto: string;
  meta: string;
  cantidad: string;
  unidadMedida: string;
  tiempo: string;
  frecuencia: string;
  constructor(idParticipante?: string, objetivoPuesto?: string, funcionPuesto?: string, actividadPuesto?: string, meta?: string, cantidad?: string, unidadMedida?: string, tiempo?: string, frecuencia?: string) {
    if (idParticipante) {
      this.idParticipante = idParticipante;
      this.objetivoPuesto = objetivoPuesto;
      this.funcionPuesto = funcionPuesto;
      this.actividadPuesto = actividadPuesto;
      this.meta = meta;
      this.cantidad = cantidad;
      this.unidadMedida = unidadMedida;
      this.tiempo = tiempo;
      this.frecuencia = frecuencia;
    }
  }
}

