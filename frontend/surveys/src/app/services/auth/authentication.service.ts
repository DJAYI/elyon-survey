import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthResponse, LoginUser } from '../../schemas/auth-response';
import { NotifyService } from '../utils/notification/notify.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  router = inject(Router);

  url = 'http://localhost:8080/';
  constructor(private http: HttpClient, private notifyService: NotifyService) { }

  public login(credentials: LoginUser) {
    return this.http.post<AuthResponse>('http://localhost:8080/api/v1/auth/login', credentials, {
      withCredentials: true
    });
  }

  public logout() {
    // Logout logic here
    this.http.post('http://localhost:8080/api/v1/auth/logout', {}, {
      withCredentials: true
    }).subscribe({
      next: () => {
        this.router.navigate(['/auth']);
        this.stopAuthIntervalVerifier();
        this.notifyService.showSuccess('Logged out', 'You have been logged out successfully');
      },
      error: e => console.log("Error: " + e)
    });
  }

  private authCheckInterval: NodeJS.Timeout | null = null;

  public startAuthIntervalVerifier() {
    this.stopAuthIntervalVerifier(); // Ensure no multiple intervals are running
    this.authCheckInterval = setInterval(() => {
      this.checkAuthentication();
    }, 20000);
  }

  private checkAuthentication() {
    this.http.get<boolean>('http://localhost:8080/api/v1/auth/admin', {
      withCredentials: true
    }).subscribe({
      next: (isAuthenticated) => {
        this.handleAuthenticationResponse(isAuthenticated);
      },
      error: () => {
        this.handleAuthenticationResponse(false);
        this.notifyService.showError('Error', 'An error occurred while trying to verify authentication');
      }
    });
  }

  private handleAuthenticationResponse(isAuthenticated: boolean) {
    if (!isAuthenticated) {
      this.router.navigate(['/auth']);
      this.stopAuthIntervalVerifier();
    } else {
      console.log('User is authenticated');
    }
  }

  public stopAuthIntervalVerifier() {
    if (this.authCheckInterval) {
      clearInterval(this.authCheckInterval);
      this.authCheckInterval = null;
    }
  }
}
