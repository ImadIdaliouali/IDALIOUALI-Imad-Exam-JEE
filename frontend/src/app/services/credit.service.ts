import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credit, CreditApplication, Repayment } from '../models/credit.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CreditService {
  private readonly API_URL = `${environment.apiUrl}/api`;

  constructor(private http: HttpClient) {}

  // Credit endpoints
  getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.API_URL}/credits`);
  }

  getCreditById(id: number): Observable<Credit> {
    return this.http.get<Credit>(`${this.API_URL}/credits/${id}`);
  }

  getClientCredits(clientId: number): Observable<Credit[]> {
    return this.http.get<Credit[]>(
      `${this.API_URL}/clients/${clientId}/credits`
    );
  }

  applyForCredit(application: CreditApplication): Observable<Credit> {
    return this.http.post<Credit>(`${this.API_URL}/credits/apply`, application);
  }

  approveCredit(id: number): Observable<Credit> {
    return this.http.put<Credit>(`${this.API_URL}/credits/${id}/approve`, {});
  }

  rejectCredit(id: number): Observable<Credit> {
    return this.http.put<Credit>(`${this.API_URL}/credits/${id}/reject`, {});
  }

  // Repayment endpoints
  getCreditRepayments(creditId: number): Observable<Repayment[]> {
    return this.http.get<Repayment[]>(
      `${this.API_URL}/credits/${creditId}/repayments`
    );
  }

  makeRepayment(creditId: number, amount: number): Observable<Repayment> {
    return this.http.post<Repayment>(`${this.API_URL}/repayments`, {
      creditId,
      amount,
    });
  }
}
