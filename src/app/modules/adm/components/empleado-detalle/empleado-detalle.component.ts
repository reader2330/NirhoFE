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
    documentoComprobanteDomicilio: null,
    estadoCivil : false
  };
  nacionalidades = [];
  bancos = [];
  hipotecas = [
  ];
  escolaridades = [
  ];
  tipoContrato = [
    ];
  tipoContacto = [
  ];
  puestos = [
  ];


  constructor(private CatalogoAdm:CatalogsAdmService) { }
  ngOnInit() {
    let idEmpleado = sessionStorage.getItem('idEmpleado');
    this.getEmpleado(idEmpleado);
    this.getNacionality();
    this.getBank();
    this.getScholarship();
    this.getJob();
    this.getTypeContac();
    this.getCreditoHipotecario()
  }

  getEmpleado(id) {

    this.CatalogoAdm.getEmploye(id).subscribe(res => {
      this.empleado = res;
      this.empleado.laboral[0]['fechaTermino'] = new Date(this.empleado.laboral[0]['fechaTermino']);
      this.empleado.laboral[0]['fechaInicio'] = new Date(this.empleado.laboral[0]['fechaInicio']);
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
        console.log(res);
        this.escolaridades = res;
      }
    });
    this.CatalogoAdm.getTypeContac().subscribe(res => {
      console.log(res);
    });
  }
  goBandeja(){
    this.responseChildren.emit({value: 1});
  }
  getJob() {
    this.CatalogoAdm.getJob().subscribe((res) => {
      if (res) {
        console.log("puestos", res);
        this.puestos = res;
      }
    });
  }
  getTypeContac() {
    this.CatalogoAdm.getTypeContac().subscribe((res) => {
      if (res) {
        this.tipoContacto = res;
      }
    });
  }
  getCreditoHipotecario(){
    this.CatalogoAdm.getCreditoHipotecario().subscribe(res => {
      this.hipotecas = res;
    });
  }

}
