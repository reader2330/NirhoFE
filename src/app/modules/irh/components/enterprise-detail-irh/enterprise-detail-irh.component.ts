import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Enterprise} from '../../models/enterprise.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';
import swal from 'sweetalert2';
import Swal from "sweetalert2";
import {EnterprisesService} from '../../services/enterprises.service';


@Component({
  selector: 'app-enterprise-detail-irh',
  templateUrl: './enterprise-detail-irh.component.html',
  styleUrls: ['./enterprise-detail-irh.component.scss']
})
export class EnterpriseDetailIrhComponent implements OnInit {
  @Output () response = new EventEmitter();
  mobile = false;
  spins = [];
  countries = [];
  mgsInit = '';
  data: Enterprise[];
  enterpriseForm = new FormGroup(
    {
      empresa : new FormGroup({
          direccion: new FormControl('', Validators.required),
          giro: new FormControl(0, Validators.required),
          pais: new FormControl(0, Validators.required),
          rfc: new FormControl('', Validators.required),
          empresa: new FormControl('', Validators.required),
          anioInicioOperaciones: new FormControl('', Validators.required),
          facturacionAnual: new FormControl('', Validators.required),
          productoServicioEstrella: new FormControl(null, Validators.required),
          principalesProductosServicios: new FormControl(null,Validators.required),
          noEmpleadosAdministrativo: new FormControl(''),
          noEmpleadosOperativo: new FormControl(''),
          tipoContratacionEmpleados: new FormControl('', Validators.required)
        }),
      entrevistado : new FormGroup({
        nombreResponsableLlenado: new FormControl('', Validators.required),
        puestoResponsableLlenado: new FormControl('', Validators.required),
        nombreEntrevistador: new FormControl('', Validators.required),
        nombreEntrevistado: new FormControl('', Validators.required),
        puestoEntrevistado: new FormControl('', Validators.required),
        correoElectronico: new FormControl('', [Validators.required, Validators.email]),
        telefonoCelular: new FormControl('', Validators.required),
        telefono_oficina_extension: new FormControl('', Validators.required)
      })
    }
  );

  constructor( breakpointObserver: BreakpointObserver, private CatalogService: CatalogsService, private EntrepiseService: EnterprisesService) {
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
    console.log('llego aqui');
    this.mgsInit = 'Registrar Empresa';
    this.CatalogService.getCountries().subscribe(res => {
      this.countries = res;
      console.log(res);
    });
    this.CatalogService.getGiros().subscribe(res => {
      this.spins = res;
      console.log(res);
    });
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

  getValuesJsonfromForm() {
      Object.keys(this.data).forEach(name => {
        if (this.enterpriseForm.controls[name]) {
          console.log(name);
          this.enterpriseForm.controls[name].setValue( this.data[name]);
        }
      });
  }
  getValuesFormFromJson(){

  }

  saveCompany() {
    console.log(this.enterpriseForm.value);
    Swal({
      title: '',
      text: 'Seguro que quieres guardar la información ingresada de la empresa',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        let obj = this.enterpriseForm.value;
        console.log(obj);
          this.EntrepiseService.saveEntripise(obj).subscribe((res) => {
              console.log(res);
              Swal(
                'Listo.',
                'La información se guardo correctamente',
                'success'
              ).then(() => {
                this.response.emit({value: 1});
              });

            },
            (err) => {
              console.log(err);
              Swal(
                'Algo salio mal.',
                'No se pudo guarda la información',
                'error'
              ).then(() => {
                this.response.emit({value: 1});
              });


            });


        }
    });
  }

  updateCompany(){

  }

}
