export interface AuthResponse {
  usernmae: string;
  message: string;
  token: string;
}

export interface LoginUser {
  username: string;
  password: string;
}