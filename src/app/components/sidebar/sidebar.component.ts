import { Component, OnInit } from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  mobile = false;
  selectModule = 9;
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
    setTimeout(() => {
      this.getModules();
      this.getUser();
    },1500);
  }

  goModule(opt) {
    this.selectModule = opt;
  }
  getModules() {
    this.LoginService.getModules().subscribe((res) => {
      console.log(res);
      this.modules = res;
    });
  }
  getUser() {
    this.LoginService.getUser().subscribe((res) => {
      console.log(res)
      this.user = res;
    });
  }
  recibirRespuestChildren(evt) {
    console.log(evt);
    if (evt.value) {
      this.selectModule = evt.value;
    }

  }





}
