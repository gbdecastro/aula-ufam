import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css'],
})
export class LoadingComponent {
  @Input() diameter = 50;

  get imgSize(): number {
    return this.diameter - 25;
  }
}
