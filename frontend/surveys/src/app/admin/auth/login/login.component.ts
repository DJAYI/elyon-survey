import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SsrCookieService } from 'ngx-cookie-service-ssr';
import { ToastModule } from 'primeng/toast';
import { AuthenticationService } from '../../../services/auth/authentication.service';
import { NotifyService } from '../../../services/utils/notification/notify.service';

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

  constructor(
    public authService: AuthenticationService,
    public cookieService: SsrCookieService,
    router: Router,
    private notifyService: NotifyService
  ) {
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
      this.notifyService.showInfo('Logging in', 'Please wait while we log you in');


      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          if (response) {
            this.status = true;
          } else {
            this.status = false;
            this.notifyService.showError('Error', 'Invalid credentials');
          }
        },
        error: () => {
          this.status = false;
          this.notifyService.showError('Error', 'An error occurred while trying to log in');
        },
        complete: () => {
          if (this.status) {
            this.notifyService.showSuccess('Success', 'Logged in successfully');
            this.router.navigate(['/admin']);
          } else {
            this.notifyService.showError('Error', 'Invalid credentials');
          }
        }
      });
    }
  }

}
