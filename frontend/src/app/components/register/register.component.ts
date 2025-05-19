import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../models/user.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  template: `
    <div class="register-container">
      <div class="card">
        <h2>Register</h2>
        <form [formGroup]="registerForm" (ngSubmit)="onSubmit()">
          <div class="form-group">
            <label for="firstName">First Name</label>
            <input
              type="text"
              id="firstName"
              formControlName="firstName"
              class="form-control"
            />
            <div
              *ngIf="
                registerForm.get('firstName')?.invalid &&
                registerForm.get('firstName')?.touched
              "
              class="error"
            >
              First name is required
            </div>
          </div>

          <div class="form-group">
            <label for="lastName">Last Name</label>
            <input
              type="text"
              id="lastName"
              formControlName="lastName"
              class="form-control"
            />
            <div
              *ngIf="
                registerForm.get('lastName')?.invalid &&
                registerForm.get('lastName')?.touched
              "
              class="error"
            >
              Last name is required
            </div>
          </div>

          <div class="form-group">
            <label for="email">Email</label>
            <input
              type="email"
              id="email"
              formControlName="email"
              class="form-control"
            />
            <div
              *ngIf="
                registerForm.get('email')?.invalid &&
                registerForm.get('email')?.touched
              "
              class="error"
            >
              <span *ngIf="registerForm.get('email')?.errors?.['required']"
                >Email is required</span
              >
              <span *ngIf="registerForm.get('email')?.errors?.['email']"
                >Enter a valid email</span
              >
            </div>
          </div>

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
                registerForm.get('username')?.invalid &&
                registerForm.get('username')?.touched
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
                registerForm.get('password')?.invalid &&
                registerForm.get('password')?.touched
              "
              class="error"
            >
              <span *ngIf="registerForm.get('password')?.errors?.['required']"
                >Password is required</span
              >
              <span *ngIf="registerForm.get('password')?.errors?.['minlength']"
                >Password must be at least 6 characters</span
              >
            </div>
          </div>

          <div *ngIf="errorMessage" class="alert alert-danger">
            {{ errorMessage }}
          </div>

          <button
            type="submit"
            [disabled]="registerForm.invalid || isLoading"
            class="btn btn-primary"
          >
            {{ isLoading ? 'Registering...' : 'Register' }}
          </button>

          <div class="login-link">
            Already have an account? <a routerLink="/login">Login</a>
          </div>
        </form>
      </div>
    </div>
  `,
  styles: [
    `
      .register-container {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: #f5f5f5;
        padding: 2rem 0;
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

      .login-link {
        text-align: center;
        margin-top: 1rem;
      }
    `,
  ],
})
export class RegisterComponent {
  registerForm: FormGroup;
  isLoading = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    const registerRequest: RegisterRequest = {
      firstName: this.registerForm.value.firstName,
      lastName: this.registerForm.value.lastName,
      email: this.registerForm.value.email,
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
    };

    this.authService.register(registerRequest).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage =
          error.error?.message || 'Registration failed. Please try again.';
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }
}
