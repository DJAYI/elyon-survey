import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { environment } from '../../../environments/environment';

export const authGuard: CanActivateFn = (_route, state) => {

  const apiUrl = environment.API_URL;
  const router: Router = inject(Router);
  const http: HttpClient = inject(HttpClient);

  // Check if the user is authenticated by checking the cookie

  const authVerification = lastValueFrom(http.get<boolean>(apiUrl + 'auth/admin', {
    withCredentials: true
  }));

  return authVerification.then(data => {
    if (data) {
      return true;
    } else {
      router.navigate(['/auth/']);
      return false;
    }
  }).catch(e => {
    console.log("Error: " + e);
    router.navigate(['/auth/']);
    return false;
  });
};
