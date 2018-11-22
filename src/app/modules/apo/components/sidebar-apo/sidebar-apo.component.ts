import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-apo',
  templateUrl: './sidebar-apo.component.html',
  styleUrls: ['./sidebar-apo.component.scss']
})
export class SidebarApoComponent implements OnInit {

  mobile = false;
  selectModule = 1;
  modules =[]

  constructor(breakpointObserver: BreakpointObserver, private route: Router, private LoginServices: LoginService) {
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

  goModule(opt) {
    this.selectModule = opt;
  }

  ngOnInit() {
  }

  cerraSesion(){}

}
