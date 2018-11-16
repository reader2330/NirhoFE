import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-empleado-detalle',
  templateUrl: './empleado-detalle.component.html',
  styleUrls: ['./empleado-detalle.component.scss']
})
export class EmpleadoDetalleComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();
  empleado = {
    id: null,
    nombreCompleto: '',
    nacionalidad: 0,
    fechaNacimiento: '',
    edad: 0,
    rfc: '',
    curp: '',
    nss: '',
    direccion: '',
    contactos: [],
    banco: 0,
    bancoCuenta: null,
    bancoClaveInterbancaria: 0,
    creditoHipotecario: false,
    tipoCreditoHipotecario: 0,
    pensionAlimenticia: false,
    escolaridad: 0,
    escolaridadCarrera: '',
    escolaridadEspecialidad: '',
    escolaridadCapacidades: '',
    escolaridadCertificaciones: [],
    escolaridadCursos: [],
    escolaridadOficios: [],
    titulo: false,
    idiomas: [],
    laboral : [],
    documentoCurp: null,
    documentoIne: null,
    documentoCV: null,
    documentoComprobanteDomicilio: null
  };
  nacionalidades = [];
  bancos = [];
  hipotecas = [
    {
      id: 1,
      descripcionCatalogo: 'INFONAVIT'
    },
    {
      id: 2,
      descripcionCatalogo: 'FOVISSTE'
    },
    {
      id: 3,
      descripcionCatalogo : 'FONACOT'
    },
    {
      id: 4,
      descripcionCatalogo : 'OTRO'
    }
  ];
  escolaridades = [{
    id:1,
    descripcionCatalogo:"Primaria Trunca"
  },
    {
      id:2,
      descripcionCatalogo:"Primaria Completa"
    },
    {
      id:3,
      descripcionCatalogo:"Secundaria Trunca"
    },
    {
      id:4,
      descripcionCatalogo:"Secundaria Completa"
    },
    {
      id:5,
      descripcionCatalogo:"Carrera Técnica Completa"
    },
    {
      id:6,
      descripcionCatalogo:"Carrera Técnica Incompleta"
    },
    {
      id:7,
      descripcionCatalogo:"Bachillerato Completo"
    },
    {
      id:8,
      descripcionCatalogo:"Bachillerato Incompleto"
    },
    {
      id:9,
      descripcionCatalogo:"Universidad Completa"
    },
    {
      id:10,
      descripcionCatalogo:"Universidad Incompleta"
    },
    {
      id:11,
      descripcionCatalogo:"Maestría Completa"
    },
    {
      id:12,
      descripcionCatalogo:"Maestría Incompleta"
    },
    {
      id:13,
      descripcionCatalogo:"Doctorado Completo"
    },
    {
      id:14,
      descripcionCatalogo:"Doctorado Incompleto"
    },
    {
      id:15,
      descripcionCatalogo:"Universitario Técnico Completo"
    },
    {
      id:16,
      descripcionCatalogo:"Universitario Técnico Incompleto"
    },
    {
      id:17,
      descripcionCatalogo:"Otro"
    }
  ];
  tipoContrato : [
    {
      id:1,
      descripcionCatalogo:"De ley"
    },
    {
      id:2,
      descripcionCatalogo:"Superiores de ley"
    },
    {
      id:3,
      descripcionCatalogo:"Honorarios"
    },
    {
      id:4,
      descripcionCatalogo:"Salarios asimilados"
    },
    {
      id:5,
      descripcionCatalogo:"Comisionista"
    },
    {
      id:6,
      descripcionCatalogo:"Becario"
    },
    {
      id:7,
      descripcionCatalogo:"Servicio social"
    },
    {
      id:8,
      descripcionCatalogo:"Otro"
    }
    ]

  constructor(private CatalogoAdm:CatalogsAdmService) { }
  ngOnInit() {
    let idEmpleado = sessionStorage.getItem('idEmpleado');
    this.getEmpleado(idEmpleado);
    this.getNacionality();
    this.getBank();
    //this.getScholarship();
  }

  getEmpleado(id) {

    this.CatalogoAdm.getEmploye(id).subscribe(res => {
      this.empleado = res;
      console.log(res);
    });

  }
  getNacionality () {
    this.CatalogoAdm.getNacionality().subscribe((res) => {
      if (res) {
        this.nacionalidades = res;
      }
    });
  }
  getBank() {
    this.CatalogoAdm.getBank().subscribe((res) => {
      if (res) {
        this.bancos = res;
      }
    });
  }

  updateEmpleado() {
    Swal({
      title: '',
      text: '¿Seguro qué quieres actualizar los datos?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {

        this.CatalogoAdm.updateEmploye(this.empleado).subscribe(res => {
          console.log(res);
          Swal('Listo.',
            'El empleado se guardo correctamente',
            'success');
          this.responseChildren.emit({value: 1});
        });
      }
    });
  }
  checkValorCredito() {
    if (!this.empleado.creditoHipotecario) {
      this.empleado.tipoCreditoHipotecario = 0;
    }
  }
  getScholarship() {
    this.CatalogoAdm.getScholarship().subscribe((res) => {
      if (res) {
        this.escolaridades = res;
      }
    });
  }
  goBandeja(){
    this.responseChildren.emit({value: 1});
  }

}
