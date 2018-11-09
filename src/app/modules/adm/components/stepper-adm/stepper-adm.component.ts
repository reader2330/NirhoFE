import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';

@Component({
  selector: 'app-stepper-adm',
  templateUrl: './stepper-adm.component.html',
  styleUrls: ['./stepper-adm.component.scss']
})
export class StepperAdmComponent implements OnInit {

  @Output() responseChildren = new EventEmitter();
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
    this.responseChildren.emit({value: evt.key});

  }

}
