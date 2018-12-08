import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {ProyectoEvdService} from '../../../evd/services/proyecto-evd.service';
import {EnterprisesService} from '../../../irh/services/enterprises.service';
import Swal from "sweetalert2";
import {ProyectoApoService} from '../../services/proyecto-apo.service';

@Component({
  selector: 'app-contact-apo',
  templateUrl: './contact-apo.component.html',
  styleUrls: ['./contact-apo.component.scss']
})
export class ContactApoComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;
  tipos = [];

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
      telefono: 0,
      puesto: '',
      tipoContacto: 0,
      email: '',
      nombre: '',
      celular: 0,
      empresa: null
    }
  }

  contactForm = new FormGroup(
    {
      nombre: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      puesto: new FormControl('', Validators.required),
      tipoContacto: new FormControl(0, Validators.required),
      telefono: new FormControl(0, Validators.required),
      celular: new FormControl(0, Validators.required),
    }
  );

  constructor( breakpointObserver: BreakpointObserver, private ProyectoApoServices: ProyectoApoService) {
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
    this.getTypeContact();
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

  getTypeContact() {
    this.ProyectoApoServices.getTypeContact().subscribe((res) => {
      if (res) {
        //console.log(res);
        this.tipos = res;
      }
    });
  }

  guardarContacto() {
    console.log(sessionStorage);
    let temp = this.contactForm.value;
    sessionStorage.setItem('contact', JSON.stringify(temp));

    let enterprise = JSON.parse(sessionStorage.getItem('enterprise'));
    let proyecto = JSON.parse(sessionStorage.getItem('project'));
    let contacto = JSON.parse(sessionStorage.getItem('contact'));

    console.log('enterprise: ', enterprise)
    console.log('proyecto: ', proyecto)
    console.log('contacto: ', contacto)

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
    this.jsonFinal.idContacto.nombre = contacto.nombre;
    this.jsonFinal.idContacto.email = contacto.email;
    this.jsonFinal.idContacto.puesto = contacto.puesto;
    this.jsonFinal.idContacto.tipoContacto = contacto.tipoContacto;
    this.jsonFinal.idContacto.telefono = contacto.telefono;
    this.jsonFinal.idContacto.celular = contacto.celular;

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
        this.ProyectoApoServices.saveProyect(this.jsonFinal).subscribe(res => {
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
