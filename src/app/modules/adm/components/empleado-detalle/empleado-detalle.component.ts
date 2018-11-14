import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-empleado-detalle',
  templateUrl: './empleado-detalle.component.html',
  styleUrls: ['./empleado-detalle.component.scss']
})
export class EmpleadoDetalleComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();
  empleado = {};
  nacionalidades = [];

  constructor(private CatalogoAdm:CatalogsAdmService) { }
  ngOnInit() {
    let idEmpleado = sessionStorage.getItem('idEmpleado');
    this.getEmpleado(idEmpleado);
    this.getNacionality();
  }

  getEmpleado(id) {

    this.CatalogoAdm.getEmploye(id).subscribe(res => {
      this.empleado = res;
      console.log(res);
    })

  }
  getNacionality () {
    this.CatalogoAdm.getNacionality().subscribe((res) => {
      if (res) {
        this.nacionalidades = res;
      }
    });
  }

  updateEmpleado(){
    Swal({
      title: '',
      text: '¿Seguro qué quieres actualizar los datos?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, guardar',
      cancelButtonText: 'No, seguir editando'
    }).then((result) => {
      if (result.value) {

        this.CatalogoAdm.updateEmploye(this.empleado).subscribe(res => {
          console.log(res);
          Swal('Listo.',
            'El empleado se guardo correctamente',
            'success');
          this.responseChildren.emit({value: 1});
        });
      }
    });
  }

}
