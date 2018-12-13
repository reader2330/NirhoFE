export class HCAmpliado {
  idParticipante: string;
  objetivoPuesto: string;
  funciones: string;
  actividades: string;
  metaKpi: string;
  cantidadMeta: string;
  unidadMedida: string;
  tiempo: string;
  frecuenciaEval: string;
  constructor(idParticipante?: string, objetivoPuesto?: string, funcionPuesto?: string, actividadPuesto?: string, meta?: string, cantidad?: string, unidadMedida?: string, tiempo?: string, frecuencia?: string) {
    if (idParticipante) {
      this.idParticipante = idParticipante;
      this.objetivoPuesto = objetivoPuesto;
      this.funciones = funcionPuesto;
      this.actividades = actividadPuesto;
      this.metaKpi = meta;
      this.cantidadMeta = cantidad;
      this.unidadMedida = unidadMedida;
      this.tiempo = tiempo;
      this.frecuenciaEval = frecuencia;
    }
  }
}
