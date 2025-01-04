import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../services/auth/authentication.service';

@Component({
    selector: 'app-login',
    imports: [ReactiveFormsModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {

  cookie: string;
  loginForm: FormGroup;

  username: FormControl;
  password: FormControl;

  constructor(public authService: AuthenticationService) {
    this.cookie = '';
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
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
    });
  }

}
