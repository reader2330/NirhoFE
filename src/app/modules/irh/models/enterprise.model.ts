import {FormControl, Validators} from '@angular/forms';

export class Enterprise {

  empresa: {
    direccion: string
    giro: number
    pais: number
    rfc: string
    empresa: string
    anioInicioOperaciones: number
    facturacionAnual: number
    productoServicioEstrella: string
    principalesProductosServicios: string
    noEmpleadosAdministrativo: number
    noEmpleadosOperativo: number
    tipoContratacionEmpleados: string
  };
  entrevistado: {
      nombreResponsableLlenado: string
      puestoResponsableLlenado: string
      nombreEntrevistador: string
      nombreEntrevistado: string
      puestoEntrevistado: string
      correoElectronico: string
      telefonoCelular: number
      telefono_oficina_extension: number;
  };

  constructor() {}

}
