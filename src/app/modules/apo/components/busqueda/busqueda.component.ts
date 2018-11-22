import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {EnterprisesService} from '../../../irh/services/enterprises.service';
import {Enterprise} from '../../../irh/models/enterprise.model';

@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.scss']
})
export class BusquedaComponent implements OnInit {

  rfc = '';
  obj = {
    enterprise_rfc : ''
  };
  id = 0;
  mobile = false;
  enterpriseSearch = new FormGroup(
    {
      enterprise_rfc: new FormControl('')
    }
  );

  constructor(private EnterprisesServices: EnterprisesService) { }

  enterprises: Enterprise [];
  @Output() responseChildren = new EventEmitter();

  displayedColumns: string[] = ['funcion', 'actividad', 'meta', 'cantidad', 'unidad', 'frecuencia', 'programado'];
  dataSource = [];

  ngOnInit() {
  }

}
