import { Component, OnInit } from '@angular/core';
import {Enterprise} from '../../models/enterprise.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CatalogsService} from '../../../clb/services/catalogs.service';

@Component({
  selector: 'app-enterprise-detail-irh',
  templateUrl: './enterprise-detail-irh.component.html',
  styleUrls: ['./enterprise-detail-irh.component.scss']
})
export class EnterpriseDetailIrhComponent implements OnInit {
  mobile = false;
  spins = [];
  countries = [];
  mgsInit = '';
  data: Enterprise[];
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
      empresa: new FormControl('', Validators.required),
      nombreResponsableLlenado: new FormControl('', Validators.required),
      puestoResponsableLlenado: new FormControl('', Validators.required),
      nombreEntrevistador: new FormControl('', Validators.required),
      nombreEntrevistado: new FormControl('', Validators.required),
      puestoEntrevistado: new FormControl('', Validators.required),
      correoElectornico: new FormControl('', Validators.required),
      telefonoCelular: new FormControl('', Validators.required),
      telefono_oficina_extension: new FormControl('', Validators.required),


    }
  );
  constructor(private CatalogServices: CatalogsService) { }

  ngOnInit() {
    console.log('llego aqui');
    if (sessionStorage.getItem('detailIRH')) {
      this.data = JSON.parse(sessionStorage.getItem('detailIRH'));
      console.log(this.data);
      this.getValuesJsonfromForm();
      this.mgsInit = 'Editar Empresa';
    } else {
      this.mgsInit = 'Registrar Empresa';
    }
    this.CatalogServices.getCountries().subscribe(res => {
      this.countries = res;
      console.log(res);
    });
    this.CatalogServices.getGiros().subscribe(res => {
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

}
