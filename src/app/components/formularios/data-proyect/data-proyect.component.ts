import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../services/catalogs.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import Swal from 'sweetalert2'
import {ProyectoService} from '../../../services/proyecto.service';

@Component({
  selector: 'app-data-proyect',
  templateUrl: './data-proyect.component.html',
  styleUrls: ['./data-proyect.component.scss']
})
export class DataProyectComponent implements OnInit {

  proyect = {
    idProyecto: 0,
    nombre: '',
    fechaRegistro: null,
    fechaFin: null,
    diasGarantia: null,
    numEmpleados: 0,
    sedes: '',
    numParticipantes: 0,
    frecuenciaEval: 0,
    idEmpresa: {},
    idContacto: {},
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

  ];
  mobile = false;
  proyectForm = new FormGroup(
    {
      numEmpleados: new FormControl('', Validators.required),
      sedes: new FormControl('', Validators.required),
      frecuenciaEval: new FormControl(0, Validators.required),
      numParticipantes: new FormControl('', Validators.required),
      nombre: new FormControl('', Validators.required)
    }
  );
  constructor(breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private ProyectoService:ProyectoService) {
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
  saveProyect() {

    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada del proyecto',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        if (sessionStorage.getItem('contact')) {
          const contact = JSON.parse(sessionStorage.getItem('contact'));
          this.proyect = this.proyectForm.value;
          this.proyect.idEmpresa = contact.empresa;
          this.proyect.idContacto = contact;
          console.log(this.proyect);
          console.log(this.proyect);
          this.ProyectoService.saveProyect(this.proyect).subscribe((res) => {
            console.log(res);
              Swal(
                'Listo.',
                'La información se guardo correctamente',
                'success'
              );


            },
            (err) => {
            console.log(err)
              Swal(
                'Algo salio mal.',
                'No se pudo guarda la información',
                'error'
              );

            });


        }

      }
    });

  }

}
