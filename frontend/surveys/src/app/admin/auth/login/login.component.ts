import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SsrCookieService } from 'ngx-cookie-service-ssr';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { lastValueFrom } from 'rxjs';
import { AuthenticationService } from '../../../services/auth/authentication.service';
import { ToastService } from '../../../services/utils/toast/toast.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink, ToastModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})

export class LoginComponent {
  status = false;

  router: Router;
  loginForm: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(public authService: AuthenticationService, public cookieService: SsrCookieService, router: Router, public toastService: ToastService, public messageService: MessageService) {
    this.router = router;

    this.username = new FormControl('', [
      Validators.required,
      Validators.minLength(4)
    ]);
    this.password = new FormControl('', [
      Validators.required,
      Validators.minLength(4)
    ]);

    this.loginForm = new FormGroup({
      username: this.username,
      password: this.password
    });
  }

  login() {
    if (this.loginForm.valid) {
      this.showLoadingToast();
      lastValueFrom(this.authService.login(this.loginForm.value)).then((response) => {
        if (response) {
          this.status = true;
        } else {
          this.status = false;
        }
      }).finally(() => {
        if (this.status) {
          this.showSuccessToast();
          this.router.navigate(['/admin']);
        } else {
          this.showErrorToast();
        }
      });
    }
  }

  showSuccessToast() {
    this.toastService.addToastMessage('success', 'Inicio de sesión exitoso', 'Inicio de sesión exitoso, redirigiendo...');
    this.toastService.showToasts();
  }

  showErrorToast() {
    this.toastService.addToastMessage('error', 'Inicio de sesión fallido', 'Inicio de sesión fallido, verifique sus credenciales');
    this.toastService.showToasts();

  }

  showLoadingToast() {
    this.toastService.addToastMessage('info', 'Iniciando sesión', 'Iniciando sesión...');
    this.toastService.showToasts();

  }

}
