import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn) {
    return true;
  }

  router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
  return false;
};

export const roleGuard = (allowedRoles: string[]): CanActivateFn => {
  return (route, state) => {
    const authService = inject(AuthService);
    const router = inject(Router);

    if (!authService.isLoggedIn) {
      router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      return false;
    }

    const hasRole = allowedRoles.some((role) => authService.hasRole(role));
    if (!hasRole) {
      router.navigate(['/forbidden']);
      return false;
    }

    return true;
  };
};
