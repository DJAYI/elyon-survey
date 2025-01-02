import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StepperComponent } from "./components/stepper/stepper.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, StepperComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'surveys';
}
