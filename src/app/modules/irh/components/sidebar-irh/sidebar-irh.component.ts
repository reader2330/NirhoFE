import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';


@Component({
  selector: 'app-sidebar-irh',
  templateUrl: './sidebar-irh.component.html',
  styleUrls: ['./sidebar-irh.component.scss']
})
export class SidebarIrhComponent implements OnInit {

  mobile = false;
  selectModule = 0;
  modules = [];
  user = {};



  constructor(breakpointObserver: BreakpointObserver, private route: Router, private LoginService: LoginService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait]).subscribe(result => {
      if (result.matches) {
        this.mobile = true;
      } else {
        this.mobile = false;
      }
    });

  }

  ngOnInit() {
    this.modules.push({
      id_submodulo: 1,
      descripcion: 'Bandeja de empresas'
    }, {
      id_submodulo: 2,
      descripcion: 'Alta de empresa'
    }, {
      id_submodulo: 3,
      descripcion: 'Configurar cuestionario'
    }, {
       id_submodulo: 4,
        descripcion: 'Contestar preguntas'
    })

    this.LoginService.getUser().subscribe(res => {
      console.log(res);
    });
  }

  goModule(opt) {
    if (opt == 2) {
      sessionStorage.clear();
    }
    this.selectModule = opt;
  }
  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }
  }

}
