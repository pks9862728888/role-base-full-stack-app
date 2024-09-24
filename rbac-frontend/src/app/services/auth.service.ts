import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LOGIN_ENDPOINT = '/api/v1/auth/login/user';

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string) {
    return this.http.post(this.LOGIN_ENDPOINT, {email, password});
  }
}
