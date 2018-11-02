export class Pregunta {
  idPregunta: number;
  enunciado: string;
  documentoReferencia: string;
  tipo: number;
  dePlantilla: number;
  tema: {
    idTema: number;
    nombre: string;
    descripcion: string;
  };
  select: boolean;

}
