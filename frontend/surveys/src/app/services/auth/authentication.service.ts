import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthResponse, LoginUser } from '../../schemas/auth-response';
import { NotifyService } from '../utils/notification/notify.service';

const API_ENDPOINTS = {
  login: 'api/v1/auth/login',
  logout: 'api/v1/auth/logout',
  adminCheck: 'api/v1/auth/admin'
};

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private readonly baseUrl = 'http://localhost:8080/';
  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);
  private readonly notifyService = inject(NotifyService);

  private authCheckInterval: number | null = null;
  private checkInterval = 20000;

  public login(credentials: LoginUser) {
    return this.http.post<AuthResponse>(
      `${this.baseUrl}${API_ENDPOINTS.login}`,
      credentials,
      { withCredentials: true }
    );
  }

  public logout() {
    this.http.post(
      `${this.baseUrl}${API_ENDPOINTS.logout}`,
      {},
      { withCredentials: true }
    ).subscribe({
      next: () => this.handleSuccessfulLogout(),
      error: (e) => console.error('Logout error:', e)
    });
  }

  public startAuthIntervalVerifier() {
    this.stopAuthIntervalVerifier();
    this.authCheckInterval = setInterval(() => {
      this.checkAuthentication();
    }, this.checkInterval) as unknown as number;
  }

  private checkAuthentication() {
    this.http.get<boolean>(
      `${this.baseUrl}${API_ENDPOINTS.adminCheck}`,
      { withCredentials: true }
    ).subscribe({
      next: (isAuthenticated) => this.handleAuthStatus(isAuthenticated),
      error: () => this.handleAuthError()
    });
  }

  private handleAuthStatus(isAuthenticated: boolean) {
    if (!isAuthenticated) {
      this.handleAuthError();
    }
  }

  private handleAuthError() {
    this.resetAuthState();
    this.notifyService.showError(
      'Error de autenticación',
      'Sesión expirada, por favor ingrese nuevamente'
    );
  }

  private handleSuccessfulLogout() {
    this.resetAuthState();
    this.notifyService.showSuccess(
      'Sesión cerrada',
      'Has cerrado sesión exitosamente'
    );
  }

  private resetAuthState() {
    this.router.navigate(['/auth']);
    this.stopAuthIntervalVerifier();
  }

  private stopAuthIntervalVerifier() {
    if (this.authCheckInterval) {
      clearInterval(this.authCheckInterval);
      this.authCheckInterval = null;
    }
  }
}