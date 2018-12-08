import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';

@Component({
  selector: 'app-stepper-apo',
  templateUrl: './stepper-apo.component.html',
  styleUrls: ['./stepper-apo.component.scss']
})
export class StepperApoComponent implements OnInit {

  @Output() response = new EventEmitter();
  mobile = false;

  control = {
    next: 0,
    back: 0
  };
  constructor(breakpointObserver: BreakpointObserver) {
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

  getResponseChildren(evt) {
    console.log(evt);
    this.response.emit({value: evt.key});

  }

  recibirRespuesta(evt) {
    console.log(evt);
    this.response.emit({value: evt.value});
  }

}
