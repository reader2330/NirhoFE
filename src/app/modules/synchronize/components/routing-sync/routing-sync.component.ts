import { Component, OnInit } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-routing-sync',
  templateUrl: './routing-sync.component.html',
  styleUrls: ['./routing-sync.component.scss']
})
export class RoutingSyncComponent implements OnInit {

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
