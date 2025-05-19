import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, RouterOutlet],
  template: `
    <div class="dashboard-container">
      <aside class="sidebar">
        <div class="user-info">
          <div class="avatar">{{ getUserInitials() }}</div>
          <div class="user-details">
            <div class="user-name">
              {{ user?.firstName }} {{ user?.lastName }}
            </div>
            <div class="user-role">{{ getUserRole() }}</div>
          </div>
        </div>

        <nav class="nav-menu">
          <ul>
            <li>
              <a
                routerLink="/dashboard"
                routerLinkActive="active"
                [routerLinkActiveOptions]="{ exact: true }"
                >Overview</a
              >
            </li>

            <!-- Client links -->
            <li *ngIf="authService.hasRole('CLIENT')">
              <a routerLink="/dashboard/my-credits" routerLinkActive="active"
                >My Credits</a
              >
            </li>
            <li *ngIf="authService.hasRole('CLIENT')">
              <a routerLink="/dashboard/apply" routerLinkActive="active"
                >Apply for Credit</a
              >
            </li>

            <!-- Employee links -->
            <li
              *ngIf="
                authService.hasRole('EMPLOYEE') || authService.hasRole('ADMIN')
              "
            >
              <a routerLink="/dashboard/clients" routerLinkActive="active"
                >Clients</a
              >
            </li>
            <li
              *ngIf="
                authService.hasRole('EMPLOYEE') || authService.hasRole('ADMIN')
              "
            >
              <a routerLink="/dashboard/credits" routerLinkActive="active"
                >Credits</a
              >
            </li>

            <!-- Admin links -->
            <li *ngIf="authService.hasRole('ADMIN')">
              <a routerLink="/dashboard/employees" routerLinkActive="active"
                >Employees</a
              >
            </li>
            <li *ngIf="authService.hasRole('ADMIN')">
              <a routerLink="/dashboard/settings" routerLinkActive="active"
                >Settings</a
              >
            </li>

            <li class="logout-item">
              <a (click)="logout()" class="logout-link">Logout</a>
            </li>
          </ul>
        </nav>
      </aside>

      <main class="content">
        <header class="dashboard-header">
          <h1>Credit Management System</h1>
        </header>

        <div class="content-area">
          <router-outlet></router-outlet>
        </div>
      </main>
    </div>
  `,
  styles: [
    `
      .dashboard-container {
        display: flex;
        min-height: 100vh;
      }

      .sidebar {
        width: 250px;
        background-color: #2c3e50;
        color: white;
        padding: 1.5rem 0;
        display: flex;
        flex-direction: column;
      }

      .user-info {
        padding: 0 1.5rem;
        display: flex;
        align-items: center;
        margin-bottom: 2rem;
      }

      .avatar {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        background-color: #3498db;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: bold;
        margin-right: 1rem;
      }

      .user-name {
        font-weight: 500;
        font-size: 1rem;
      }

      .user-role {
        font-size: 0.75rem;
        opacity: 0.8;
      }

      .nav-menu ul {
        list-style: none;
        padding: 0;
        margin: 0;
      }

      .nav-menu li {
        margin: 0.5rem 0;
      }

      .nav-menu a {
        display: block;
        padding: 0.75rem 1.5rem;
        color: rgba(255, 255, 255, 0.8);
        text-decoration: none;
        transition: all 0.3s;
      }

      .nav-menu a:hover,
      .nav-menu a.active {
        background-color: rgba(255, 255, 255, 0.1);
        color: white;
      }

      .logout-item {
        margin-top: auto !important;
        border-top: 1px solid rgba(255, 255, 255, 0.1);
        padding-top: 0.5rem;
      }

      .logout-link {
        cursor: pointer;
        color: #e74c3c !important;
      }

      .content {
        flex: 1;
        background-color: #f5f7fa;
      }

      .dashboard-header {
        background-color: white;
        padding: 1rem 2rem;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
      }

      .dashboard-header h1 {
        margin: 0;
        font-size: 1.5rem;
        color: #333;
      }

      .content-area {
        padding: 2rem;
      }
    `,
  ],
})
export class DashboardComponent implements OnInit {
  user: User | null = null;

  constructor(public authService: AuthService) {}

  ngOnInit(): void {
    this.authService.currentUser$.subscribe((user) => {
      this.user = user;
    });
  }

  getUserInitials(): string {
    if (!this.user) return '?';
    return (
      this.user.firstName.charAt(0) + this.user.lastName.charAt(0)
    ).toUpperCase();
  }

  getUserRole(): string {
    if (!this.user || !this.user.roles || this.user.roles.length === 0)
      return 'User';

    if (this.user.roles.includes('ADMIN')) return 'Administrator';
    if (this.user.roles.includes('EMPLOYEE')) return 'Employee';
    if (this.user.roles.includes('CLIENT')) return 'Client';

    return 'User';
  }

  logout(): void {
    this.authService.logout();
    window.location.href = '/login';
  }
}
