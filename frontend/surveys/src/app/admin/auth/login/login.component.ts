import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SsrCookieService } from 'ngx-cookie-service-ssr';
import { AuthenticationService } from '../../../services/auth/authentication.service';


@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})


export class LoginComponent {
  router: Router;
  loginForm: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(public authService: AuthenticationService, public cookieService: SsrCookieService, router: Router) {
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
      this.authService.login(this.loginForm.value).subscribe((res) => {
        if (res) {
          this.router.navigate(['/admin']);
        } else {
          console.log('Error');
          this.loginForm.reset();
        }
      });
    }
  }

}
