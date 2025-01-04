import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { StepperComponent } from "./components/stepper/stepper.component";

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, StepperComponent, RouterLink],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'surveys';
}
