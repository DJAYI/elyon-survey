import { Injectable } from '@angular/core';
import { ToastService } from '../toast/toast.service';

@Injectable({
  providedIn: 'root'
})
export class NotifyService {

  constructor(private toastService: ToastService) { }

  public showSuccess(summary: string, details: string) {
    this.toastService.addToastMessage('success', summary, details);
    this.toastService.showToasts();
  }

  public showError(summary: string, details: string) {
    this.toastService.addToastMessage('error', summary, details);
    this.toastService.showToasts();
  }

  public showInfo(summary: string, details: string) {
    this.toastService.addToastMessage('info', summary, details);
    this.toastService.showToasts();
  }

  public showWarn(summary: string, details: string) {
    this.toastService.addToastMessage('warn', summary, details);
    this.toastService.showToasts();
  }

  public showSecondary(summary: string, details: string) {
    this.toastService.addToastMessage('secondary', summary, details);
    this.toastService.showToasts();
  }

  public showContrast(summary: string, details: string) {
    this.toastService.addToastMessage('contrast', summary, details);
    this.toastService.showToasts();
  }


}
