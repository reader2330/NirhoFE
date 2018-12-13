import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {CustomThemeComponent} from '../custom-theme/custom-theme.component';

@Component({
  selector: 'app-configurador-cuestionario',
  templateUrl: './configurador-cuestionario.component.html',
  styleUrls: ['./configurador-cuestionario.component.scss']
})
export class ConfiguradorCuestionarioComponent implements OnInit {
  @Output() response = new EventEmitter();
  firstStage = false;
  secondStage = false;
  thirdStage = false;
  constructor() { }

  ngOnInit() {
  }

  responseChildren($evt) {
    if ($evt.value) {
      this.response.emit({value: 6});
    }
  }





}
