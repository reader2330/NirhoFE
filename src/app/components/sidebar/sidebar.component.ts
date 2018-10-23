import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  fillerNav = Array.from({length: 50}, (_, i) => `Nav Item ${i + 1}`);



  ngOnInit() {
  }

}
