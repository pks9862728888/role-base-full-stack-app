import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {ILoginResponseDto} from "../interfaces/i-login-response-dto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private LOGIN_ENDPOINT = `${environment.apiUrl}/api/v1/auth/login`;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<ILoginResponseDto> {
    return this.http.post<ILoginResponseDto>(this.LOGIN_ENDPOINT, {username, password});
  }
}
