import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {CatalogsService} from '../../../clb/services/catalogs.service';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';

@Component({
  selector: 'app-payment-adm',
  templateUrl: './payment-adm.component.html',
  styleUrls: ['./payment-adm.component.scss']
})
export class PaymentAdmComponent implements OnInit {
  mobile = false;
  payment = {
    banco: 0,
    bancoCuenta: '',
    bancoClaveInterbancaria: ''
  };
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
  bancos = [];
  paymentForm = new FormGroup(
    {
      banco: new FormControl(0, [Validators.required]),
      bancoCuenta: new FormControl('', Validators.required),
      bancoClaveInterbancaria: new FormControl('', Validators.required),
      creditoHipotecario: new FormControl(false),
      tipoCreditoHipotecario: new FormControl('') ,
      pensionAlimenticia: new FormControl(false)
    }
  );
  constructor(breakpointObserver: BreakpointObserver, private CatalogsAdmServices: CatalogsAdmService) {
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

  checkMobileCols() {
    if (this.mobile) {
      return 1;
    } else {
      return 3;
    }
  }

  getBank() {
    this.CatalogsAdmServices.getBank().subscribe((res) => {
      if (res) {
        this.bancos = res;
      }
    });
  }

  saveBank() {
    console.log(this.paymentForm.value);
    this.payment = this.paymentForm.value;
    sessionStorage.setItem('payment', JSON.stringify(this.payment));
  }


  ngOnInit() {
    this.getBank();
  }

  checkValorCredito() {
    let opt = this.paymentForm.controls['creditoHipotecario'].value;
    if (!opt) {
      this.paymentForm.patchValue({'tipoCreditoHipotecario': 0})
    }
  }

  cancelCompany(){}
}
