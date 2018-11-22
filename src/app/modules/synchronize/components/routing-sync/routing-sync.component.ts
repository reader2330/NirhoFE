import { Component, OnInit } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import {Router} from '@angular/router';
import {environment} from '../../../../../environments/environment';
import {LoginService} from '../../../clb/services/login.service';


@Component({
  selector: 'app-routing-sync',
  templateUrl: './routing-sync.component.html',
  styleUrls: ['./routing-sync.component.scss']
})
export class RoutingSyncComponent implements OnInit {

  adm = false;
  imagenes = [{
    url: environment.urlNG + 'assets/imagen1.jpeg'
  },
    {
      url: environment.urlNG + 'assets/imagen2.jpeg'
    },
    {
      url: environment.urlNG + 'assets/imagen3.jpeg'
    },
    {
      url: environment.urlNG + 'assets/imagen4.jpeg'
    },
  ];

  constructor(private router: Router, private LoginService: LoginService) { }

  goModule(num) {
    switch (num) {
      case 1: {
        this.router.navigate(['CLB']);
        break;
      }
      case 2: {
        this.router.navigate(['IRH']);
        break;
      }
      case 3: {
        this.router.navigate(['ADM']);
        break;
      }
      case 4: {
        this.router.navigate(['EVD']);
        break;
      }
      case 5: {
        this.router.navigate(['EVA360']);
        break;
      }
      case 6: {
        this.router.navigate(['APO']);
        break;
      }
      case 7: {
        this.router.navigate(['PVC']);
        break;
      }
      case 8: {
        this.router.navigate(['RYS']);
        break;
      }
    }

  }
  ngOnInit() {
    this.LoginService.getUser().subscribe(res => {
      console.log(res);
      if (res && res.rol !== 2 ) {
        this.adm = false;
      }else{
        if(res.rol === 2) {
          this.adm = true;
        }
      }
    })
  }

}
