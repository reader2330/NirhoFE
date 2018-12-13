import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {CustomTheme360Component} from '../custom-theme-360/custom-theme-360.component';

@Component({
  selector: 'app-configurador-cuestionario-360',
  templateUrl: './configurador-cuestionario-360.component.html',
  styleUrls: ['./configurador-cuestionario-360.component.scss']
})
export class ConfiguradorCuestionario360Component implements OnInit {
  @Output() response = new EventEmitter();
  firstStage = false;
  secondStage = false;
  thirdStage = false;
  constructor() { }

  ngOnInit() {
  }

  responseChildren($evt) {
    if ($evt.value) {
      this.response.emit({value: 4});
    }
  }





}
