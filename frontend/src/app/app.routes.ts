import { Routes } from '@angular/router';
import { authGuard, roleGuard } from './guards/auth.guard';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
    children: [
      // Add child routes for dashboard here
    ],
  },
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full',
  },
  // Wildcard route for 404
  {
    path: '**',
    redirectTo: '/dashboard',
  },
];
