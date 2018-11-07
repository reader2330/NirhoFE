import { Component, OnInit } from '@angular/core';
import {Enterprise} from '../../models/enterprise.model';

@Component({
  selector: 'app-enterprise-detail-irh',
  templateUrl: './enterprise-detail-irh.component.html',
  styleUrls: ['./enterprise-detail-irh.component.scss']
})
export class EnterpriseDetailIrhComponent implements OnInit {

  data: Enterprise[];

  constructor() { }

  ngOnInit() {
    console.log('llego aqui');
    if (sessionStorage.getItem('detail')) {
      this.data = JSON.parse(sessionStorage.getItem('detail'));
      console.log(this.data);
    }
  }

}
