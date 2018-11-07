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
  selectModule = 1;
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
  }

  goModule(opt) {
    this.selectModule = opt;
  }

}
