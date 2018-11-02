export class Proyecto {

  idProyecto: number;
  nombre: string;
  numEmpleados: number;
  sedes: string;
  numParticipantes: number;
  fechaRegistro: Date;
  fechaFin: Date;
  diasGarantia: number;
  frecuenciaEval: number;
  idEmpresa: {
    id: number,
    direccion: string,
    empresa: string,
    giro: number,
    pais: number,
    rfc: string,
    anioInicioOperaciones: number,
    facturacionAnual: number,
    productoServicioEstrella: number,
    principalesProductosServicios: number,
    noEmpleadosAdministrativo: number,
    noEmpleadosOperativo: number,
    tipoContratacionEmpleados: number
  };
  idContacto: {
    id: number,
    celular: number,
    email: string,
    nombre: string,
    puesto: string,
    telefono: number,
    tipoContacto: number,
    empresa: string
  };
  constructor() {}


}
