import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import {
  AuthResponse,
  LoginRequest,
  RegisterRequest,
  User,
} from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly API_URL = `${environment.apiUrl}/api/auth`;
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadUserFromStorage();
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.API_URL}/login`, request)
      .pipe(tap((response) => this.handleAuthentication(response)));
  }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.API_URL}/register`, request)
      .pipe(tap((response) => this.handleAuthentication(response)));
  }

  registerAdmin(request: RegisterRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.API_URL}/register/admin`, request)
      .pipe(tap((response) => this.handleAuthentication(response)));
  }

  registerEmployee(request: RegisterRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.API_URL}/register/employee`, request)
      .pipe(tap((response) => this.handleAuthentication(response)));
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
  }

  get isLoggedIn(): boolean {
    return !!localStorage.getItem('auth_token');
  }

  get token(): string | null {
    return localStorage.getItem('auth_token');
  }

  get currentUser(): User | null {
    return this.currentUserSubject.value;
  }

  hasRole(role: string): boolean {
    return this.currentUser?.roles?.includes(role) || false;
  }

  private handleAuthentication(response: AuthResponse): void {
    localStorage.setItem('auth_token', response.accessToken);
    localStorage.setItem('user', JSON.stringify(response.user));
    this.currentUserSubject.next(response.user);
  }

  private loadUserFromStorage(): void {
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const user = JSON.parse(userJson) as User;
      this.currentUserSubject.next(user);
    }
  }
}
