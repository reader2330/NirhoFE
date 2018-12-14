import {
  Component,
  OnInit
} from '@angular/core';
import {
  BreakpointObserver,
  Breakpoints
} from '@angular/cdk/layout';
import {
  Router
} from '@angular/router';
import {
  LoginService
} from '../../../clb/services/login.service';

@Component({
  selector: 'app-sidebar-360',
  templateUrl: './sidebar-360.component.html',
  styleUrls: ['./sidebar-360.component.scss']
})
export class Sidebar360Component implements OnInit {
  mobile = false;
  selectedItem = 1;
  selectModule = 1;
  modules = [];
  user = {};
  avatar = {
    url: ''
  };
  constructor(breakpointObserver: BreakpointObserver, private route: Router, private loginService: LoginService) {
    breakpointObserver.isMatched(('(max-width:450)'));
    breakpointObserver.observe([
      Breakpoints.HandsetLandscape, Breakpoints.HandsetPortrait
    ]).subscribe(result => {
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
    }, 1500);
  }

  goModule(opt) {
    this.selectedItem = opt;
    this.selectModule = opt;
  }
  getModules() {

    this.loginService.getModules().subscribe((res) => {
      this.modules = res;
      if (this.user['rol'] === 3) {

      }
    });
  }
  getUser() {
    this.loginService.getUser().subscribe((res) => {
      this.user = res;
      sessionStorage.setItem('user', JSON.stringify(this.user));
      if (this.user) {
        this.avatar.url = this.user['avatar'];
      }
    });
  }

  recibirRespuestChildren(evt) {
    if (evt.value) {
      this.selectModule = evt.value;
    }
  }

  cerraSesion() {
    this.loginService.closeSession().subscribe((res) => {
      console.log(res);
    });
    sessionStorage.clear();
    localStorage.clear();
    this.route.navigate(['']);
  }
  IrInicio() {
    this.route.navigate(['SYNC']);
  }
  goAvatarEditing() {
    this.route.navigate(['avatar-edit', 'EVA360']);
  }

}
