import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';

export const authGuard: CanActivateFn = (_route, state) => {


  const router: Router = inject(Router);
  const http: HttpClient = inject(HttpClient);

  // Check if the user is authenticated by checking the cookie

  const authVerification = lastValueFrom(http.get<boolean>('http://localhost:8080/api/v1/auth/admin', {
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
