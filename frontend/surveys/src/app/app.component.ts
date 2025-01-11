import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Toast } from 'primeng/toast';
import { ToastService } from './services/utils/toast/toast.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Toast],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'surveys';

  constructor(private messageService: MessageService, private toastService: ToastService) {
  }

  showToast() {
    this.messageService.add({ severity: this.toastService.toastMessageProps.severity, summary: this.toastService.toastMessageProps.summary, detail: this.toastService.toastMessageProps.detail });
  }

}