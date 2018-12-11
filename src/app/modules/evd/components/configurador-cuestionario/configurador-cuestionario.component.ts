import {Component, OnInit, ViewChild} from '@angular/core';
import {CustomThemeComponent} from '../custom-theme/custom-theme.component';

@Component({
  selector: 'app-configurador-cuestionario',
  templateUrl: './configurador-cuestionario.component.html',
  styleUrls: ['./configurador-cuestionario.component.scss']
})
export class ConfiguradorCuestionarioComponent implements OnInit {

  firstStage = false;
  secondStage = false;
  thirdStage = false;
  constructor() { }

  ngOnInit() {
  }

  toReceiveChildren($evt) {
    if ($evt.value) {
      const state = $evt.value;
      switch (state) {
        case 1:
          this.firstStage = true;
          break;
        case 2:
          this.secondStage = true;
          break;
        case 3:
          this.thirdStage = true;
          break;
      }
    }
  }





}
