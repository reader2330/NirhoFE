import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CatalogsAdmService} from '../../services/catalogs-adm.service';
import Swal from "sweetalert2";
import {MatTableDataSource} from '@angular/material';
import {laboral_interface} from '../labor-adm/labor-adm.component';


@Component({
  selector: 'app-bandeja',
  templateUrl: './bandeja-adm.component.html',
  styleUrls: ['./bandeja-adm.component.scss']
})
export class BandejaAdmComponent implements OnInit {
  @Output() responseChildren = new EventEmitter();

  constructor(private CatalogsAdmServices: CatalogsAdmService) {
  }
  displayedColumns: string[] = ['nombreCompleto', 'nacionalidad', 'fechaNacimiento', 'edad', 'rfc', 'detail3', 'delete'];
  dataSource = new MatTableDataSource<laboral_interface>();

  ngOnInit() {


    /* if (localStorage.getItem('arrayEmpleados')) {
       if (localStorage.getItem('newEmpleado')) {
         let empleado = JSON.parse(localStorage.getItem('newEmpleado'));
         let array = JSON.parse(localStorage.getItem('arrayEmpleados'));
         array.push(empleado);
         this.dataSource = array;
         localStorage.setItem('arrayEmpleados', JSON.stringify(array));
         console.log(array);
         localStorage.removeItem('newEmpleado');
       }else{
         let array = JSON.parse(localStorage.getItem('arrayEmpleados'));
         this.dataSource = array;
       }
     }else {
       let arreglo = [];
       localStorage.setItem('arrayEmpleados', JSON.stringify(arreglo));
     }
   }*/
    this.getEmpleados();

  }
  getEmpleados() {
    this.CatalogsAdmServices.getEmployes().subscribe((res) => {
      this.dataSource.data = res;
    });

  }

  goDetailEmpleado(element) {
    console.log("adios");
    if (element) {
      sessionStorage.setItem('idEmpleado', JSON.stringify(element.id));
      this.responseChildren.emit({value: 3});
    }
  }
  deleteEmpleado(element){
    console.log(element)
    Swal({
      title: '',
      text: '¿Seguro qué quieres eliminar a este empleado?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
          this.CatalogsAdmServices.deleteEmploye(element.id).subscribe(res => {
            Swal(
              'Listo.',
              'Se ha eliminado correctamente',
              'success'
            );
            this.getEmpleados();

          });


        }
    });

  }
  /*getUser() {
    if (sessionStorage.getItem('user')) {
      let user = JSON.parse(sessionStorage.getItem('user'));
      if (user.rol !== 3) {
        this.getProyects();
      } else {
        this.getProyects();
      }
    } else {
      this.getProyects();
    }
  }

  getProyectsbyRol() {
    this.ProyectoService.getProyectsbyRol(4).subscribe((res) => {
        this.proyects = res;
    });
  }*/



}
