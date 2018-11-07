import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Enterprise} from '../../models/enterprise.model';
import {EnterprisesService} from '../../services/enterprises.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-enterprise-list',
  templateUrl: './enterprise-list.component.html',
  styleUrls: ['./enterprise-list.component.scss']
})

export class EnterpriseListComponent implements OnInit {
  rfc = '';
  obj = '';
  id = 0;
  enterpriseSearch = new FormGroup(
    {
      enterprise_rfc: new FormControl('')
    }
  );

  constructor(private EnterprisesServices: EnterprisesService) { }

  enterprises: Enterprise [];
  @Output() responseChildren = new EventEmitter();

  displayedColumns: string[] = ['name', 'rfc', 'address', 'detail'];
  dataSource = [];

  getEnterprises() {

    this.EnterprisesServices.getEnterprises().subscribe((res) => {
      console.log(res);
      this.enterprises = res;
      this.dataSource = this.enterprises;
    });

  }

  getEnterpriseByRFC() {
    this.obj = this.enterpriseSearch.value;
    this.rfc = this.obj.enterprise_rfc;
    this.EnterprisesServices.getEnterpriseByRFC(this.rfc).subscribe((res) => {
      console.log(res);
      this.enterprises = res;
      this.dataSource = this.enterprises;
    });
  }

  getEnterpriseDetail(obj) {
    sessionStorage.setItem('detail', JSON.stringify(obj));
    this.responseChildren.emit({value: 10});
  }

  ngOnInit() {
    this.getEnterprises();
  }

}
