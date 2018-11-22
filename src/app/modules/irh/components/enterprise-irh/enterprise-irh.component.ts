import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {EnterprisesService} from '../../services/enterprises.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-enterprise-irh',
  templateUrl: './enterprise-irh.component.html',
  styleUrls: ['./enterprise-irh.component.scss']
})
export class EnterpriseIrhComponent implements OnInit {
  @Output() response = new EventEmitter();
  mobile = false;
  mgsInit = '';
  countries = [];
  spins = [];

  enterpriseForm = new FormGroup(
    {
      id: new FormControl(null),
      anioInicioOperaciones: new FormControl(0),
      facturacionAnual: new FormControl(0),
      productoServicioEstrella: new FormControl(null),
      principalesProductosServicios: new FormControl(null),
      noEmpleadosAdministrativo: new FormControl(0),
      noEmpleadosOperativo: new FormControl(0),
      tipoContratacionEmpleados: new FormControl(null),
      direccion: new FormControl('', Validators.required),
      giro: new FormControl(0, Validators.required),
      pais: new FormControl(0, Validators.required),
      rfc: new FormControl('', Validators.required),
      empresa: new FormControl('', Validators.required)
    }
  );

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
    if (sessionStorage.getItem('detailIRH')) {
      let data = JSON.parse(sessionStorage.getItem('detailIRH'));
      console.log(data);
      this.enterpriseForm.patchValue(data);
      console.log(this.enterpriseForm.value);
      this.mgsInit = 'Editar Empresa';
    }
    this.CatalogService.getCountries().subscribe(res => {
      this.countries = res;
      console.log(res);
    });
    this.CatalogService.getGiros().subscribe(res => {
      this.spins = res;
      console.log(res);
    });
  }


  saveCompany() {
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
  updateCompany() {
  }
  cancelCompany() {
    Swal({
      title: '',
      text: '¿Se eliminara la información ingresada?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {
        this.enterpriseForm.reset();
        this.response.emit({value: 1});
      }
    });
  }

}
