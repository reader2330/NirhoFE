import {Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '../../../../../../node_modules/@angular/cdk/layout';

@Component({
  selector: 'app-steppers-360',
  templateUrl: './steppers-360.component.html',
  styleUrls: ['./steppers-360.component.scss']
})
export class Steppers360Component implements OnInit {
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
