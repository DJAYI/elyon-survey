import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})

export class ToastService {

  public toastMessageProps = {
    severity: '',
    summary: '',
    detail: ''
  }

  constructor(private messageService: MessageService) {
    this.toastMessageProps = {
      severity: '',
      summary: '',
      detail: ''
    }
  }

  addToastMessage(severity: string, summary: string, detail: string) {
    this.toastMessageProps = {
      severity: severity,
      summary: summary,
      detail: detail
    }
  }

  showToasts() {
    this.messageService.add({ severity: this.toastMessageProps.severity, summary: this.toastMessageProps.summary, detail: this.toastMessageProps.detail, life: 3000 });
  }
}
