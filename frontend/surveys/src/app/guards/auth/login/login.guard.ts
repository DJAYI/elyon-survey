import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SsrCookieService } from 'ngx-cookie-service-ssr';

export const loginGuard: CanActivateFn = (route, state) => {
  const cookieService: SsrCookieService = inject(SsrCookieService);

  const router: Router = inject(Router);

  if (!cookieService.check('access_token')) {
    return true;
  }

  router.navigate(['/admin']);
  return false;
};
