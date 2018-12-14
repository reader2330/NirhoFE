import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {EnterprisesService} from '../../../irh/services/enterprises.service';
import {ProyectoEvdService} from '../../services/proyecto-evd.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-proyect-evd',
  templateUrl: './proyect-evd.component.html',
  styleUrls: ['./proyect-evd.component.scss']
})
export class ProyectEvdComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  mgsInit = '';
  countries = [];
  spins = [];

  jsonFinal = {
    id: null,
    idEmpresa: {
      empresa: '',
      pais: 0,
      rfc: '',
      giro: 0,
      direccion: '',
    },
    nombre: '',
    numEmpleados: 0,
    numParticipantes: 0,
    sedes: '',
    frecuenciaEval: '',
    idContacto: {
      id: null,
      telefono: '',
      puesto: '',
      tipoContacto: 0,
      email: '',
      nombre: '',
      celular: '',
      empresa: null
    }
  };
  periods = [
    {
      id: 1,
      description: 'Mensual'
    },
    {
      id: 2,
      description: 'Bimestral'
    },
    {
      id: 3,
      description: 'Semestral'
    },
    {
      id: 4,
      description: 'Anual'
    },
    {
      id: 5,
      description: 'Única vez'
    }

  ];

  proyectForm = new FormGroup(
    {
      proyecto: new FormControl('', Validators.required),
      numEmpleados: new FormControl(0, Validators.required),
      numParticipantes: new FormControl(0, Validators.required),
      sede: new FormControl('', Validators.required),
      frecuenciaEval: new FormControl('', Validators.required),
    }
  );
  constructor( breakpointObserver: BreakpointObserver, private ProyectoEvdServices: ProyectoEvdService, private EntrepiseService: EnterprisesService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
        this.checkMobileCols();
      } else {
        this.mobile = false;
        this.checkMobileCols();
      }
      console.log(this.mobile);
    });
  }

  ngOnInit() {
  }

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }

  }

  checkMaxColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }

  checkMaxTwoColumns() {
    if (this.mobile) {
      return 1;
    } else {
      return 2;
    }
  }



  guardarProyecto() {
    console.log(sessionStorage);
    let temp = this.proyectForm.value;
    sessionStorage.setItem('proyect-evd', JSON.stringify(temp));

    let enterprise = JSON.parse(sessionStorage.getItem('enterprise-evd'));
    let proyecto = JSON.parse(sessionStorage.getItem('proyect-evd'));
    let contacto = JSON.parse(sessionStorage.getItem('contact-evd'));

    this.jsonFinal.idEmpresa.empresa = enterprise.empresa;
    this.jsonFinal.idEmpresa.pais = enterprise.pais;
    this.jsonFinal.idEmpresa.rfc = enterprise.rfc;
    this.jsonFinal.idEmpresa.giro = enterprise.giro;
    this.jsonFinal.idEmpresa.direccion = enterprise.direccion;
    this.jsonFinal.nombre = proyecto.proyecto;
    this.jsonFinal.numEmpleados = proyecto.numEmpleados;
    this.jsonFinal.numParticipantes = proyecto.numParticipantes;
    this.jsonFinal.sedes = proyecto.sede;
    this.jsonFinal.frecuenciaEval = proyecto.frecuenciaEval;
    this.jsonFinal.idContacto.email = contacto.email;
    this.jsonFinal.idContacto.celular = contacto.celular;
    this.jsonFinal.idContacto.telefono = contacto.telefono;
    this.jsonFinal.idContacto.tipoContacto = contacto.tipoContacto;
    this.jsonFinal.idContacto.puesto = contacto.puesto;
    this.jsonFinal.idContacto.nombre = contacto.nombre;

    console.log('json final: ', this.jsonFinal);

    Swal({
      title: '',
      text: '¿Seguro qué quieres guardar los datos?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.ProyectoEvdServices.saveProyect(this.jsonFinal).subscribe(res => {
          console.log(res);
          Swal('Listo.',
            'El proyecto se guardo correctamente',
            'success');
          this.response.emit({value: 1});
        });
      }
    });

  }

}
