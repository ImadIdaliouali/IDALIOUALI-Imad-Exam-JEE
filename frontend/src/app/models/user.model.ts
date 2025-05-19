export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password?: string;
  roles?: string[];
}

export interface AuthResponse {
  accessToken: string;
  tokenType: string;
  user: User;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
}
