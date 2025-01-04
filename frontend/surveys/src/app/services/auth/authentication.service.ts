import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginUser } from '../../model/auth-response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  url = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  login(user: LoginUser) {
    return this.http.post<any>('http://localhost:8080/api/v1/auth/login', user, {
      headers: {
        'Content-Type': 'application/json',
      }
    });
  }
}
