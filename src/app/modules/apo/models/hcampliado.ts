export class HCAmpliado {
  idParticipante: string;
  objetivoPuesto: string;
  funciones: string;
  actividades: string;
  meta: string;
  cantidad: string;
  cantidadMeta: string;
  tiempo: string;
  frecuenciaEval: string;
  constructor(idParticipante?: string, objetivoPuesto?: string, funcionPuesto?: string, actividadPuesto?: string, meta?: string, cantidad?: string, unidadMedida?: string, tiempo?: string, frecuencia?: string) {
    if (idParticipante) {
      this.idParticipante = idParticipante;
      this.objetivoPuesto = objetivoPuesto;
      this.funciones = funcionPuesto;
      this.actividades = actividadPuesto;
      this.meta = meta;
      this.cantidad = cantidad;
      this.cantidadMeta = unidadMedida;
      this.tiempo = tiempo;
      this.frecuenciaEval = frecuencia;
    }
  }
}

