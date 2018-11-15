import { Component, OnInit } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import {Router} from '@angular/router';
import {environment} from '../../../../../environments/environment';


@Component({
  selector: 'app-routing-sync',
  templateUrl: './routing-sync.component.html',
  styleUrls: ['./routing-sync.component.scss']
})
export class RoutingSyncComponent implements OnInit {

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

  constructor(private router: Router) { }

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
  }

}
