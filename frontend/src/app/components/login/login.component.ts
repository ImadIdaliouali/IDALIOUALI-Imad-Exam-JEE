import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { LoginRequest } from '../../models/user.model';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  template: `
    <div class="login-container">
      <div class="card">
        <h2>Login</h2>
        <form [formGroup]="loginForm" (ngSubmit)="onSubmit()">
          <div class="form-group">
            <label for="username">Username</label>
            <input
              type="text"
              id="username"
              formControlName="username"
              class="form-control"
            />
            <div
              *ngIf="
                loginForm.get('username')?.invalid &&
                loginForm.get('username')?.touched
              "
              class="error"
            >
              Username is required
            </div>
          </div>

          <div class="form-group">
            <label for="password">Password</label>
            <input
              type="password"
              id="password"
              formControlName="password"
              class="form-control"
            />
            <div
              *ngIf="
                loginForm.get('password')?.invalid &&
                loginForm.get('password')?.touched
              "
              class="error"
            >
              Password is required
            </div>
          </div>

          <div *ngIf="errorMessage" class="alert alert-danger">
            {{ errorMessage }}
          </div>

          <button
            type="submit"
            [disabled]="loginForm.invalid || isLoading"
            class="btn btn-primary"
          >
            {{ isLoading ? 'Logging in...' : 'Login' }}
          </button>

          <div class="register-link">
            Don't have an account? <a routerLink="/register">Register</a>
          </div>
        </form>
      </div>
    </div>
  `,
  styles: [
    `
      .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f5f5f5;
      }

      .card {
        width: 400px;
        padding: 2rem;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        background-color: white;
      }

      h2 {
        text-align: center;
        margin-bottom: 1.5rem;
        color: #333;
      }

      .form-group {
        margin-bottom: 1rem;
      }

      label {
        display: block;
        margin-bottom: 0.5rem;
        font-weight: 500;
      }

      .form-control {
        width: 100%;
        padding: 0.75rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
      }

      .error {
        color: #dc3545;
        font-size: 0.875rem;
        margin-top: 0.25rem;
      }

      .btn {
        width: 100%;
        padding: 0.75rem;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 1rem;
        cursor: pointer;
        margin-top: 1rem;
      }

      .btn:disabled {
        background-color: #6c757d;
        cursor: not-allowed;
      }

      .alert {
        padding: 0.75rem;
        margin: 1rem 0;
        border-radius: 4px;
        background-color: #f8d7da;
        color: #721c24;
        border: 1px solid #f5c6cb;
      }

      .register-link {
        text-align: center;
        margin-top: 1rem;
      }
    `,
  ],
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  errorMessage = '';
  returnUrl: string = '/';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });

    this.returnUrl =
      this.route.snapshot.queryParams['returnUrl'] || '/dashboard';
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const loginRequest: LoginRequest = {
      username: this.loginForm.value.username,
      password: this.loginForm.value.password,
    };

    this.authService.login(loginRequest).subscribe({
      next: () => {
        this.router.navigate([this.returnUrl]);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage =
          error.error?.message || 'Invalid username or password';
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }
}
