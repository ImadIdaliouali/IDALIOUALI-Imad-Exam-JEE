export interface Credit {
  id?: number;
  clientId: number;
  amount: number;
  duration: number;
  interestRate: number;
  monthlyPayment: number;
  totalPayment: number;
  status: CreditStatus;
  applicationDate: Date;
  approvalDate?: Date;
}

export enum CreditStatus {
  PENDING = 'PENDING',
  APPROVED = 'APPROVED',
  REJECTED = 'REJECTED',
  ACTIVE = 'ACTIVE',
  CLOSED = 'CLOSED',
}

export interface CreditApplication {
  clientId: number;
  amount: number;
  duration: number;
  interestRate: number;
}

export interface Repayment {
  id?: number;
  creditId: number;
  amount: number;
  paymentDate: Date;
  status: RepaymentStatus;
}

export enum RepaymentStatus {
  PENDING = 'PENDING',
  PAID = 'PAID',
  LATE = 'LATE',
  MISSED = 'MISSED',
}
