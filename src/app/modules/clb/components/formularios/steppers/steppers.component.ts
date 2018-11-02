import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../../../node_modules/@angular/cdk/layout';

@Component({
  selector: 'app-steppers',
  templateUrl: './steppers.component.html',
  styleUrls: ['./steppers.component.scss']
})
export class SteppersComponent implements OnInit {
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
