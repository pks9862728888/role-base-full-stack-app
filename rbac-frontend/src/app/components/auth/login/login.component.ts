import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from "../../../services/auth.service";
import {Router} from '@angular/router';
import {HttpConstants} from "../../../models/http-constants";
import {SessionStorageService} from "../../../services/session-storage.service";
import {ILoginResponseDto} from "../../../interfaces/i-login-response-dto";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthService,
              private sessionStorageService: SessionStorageService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    const val = this.loginForm.value;
    if (val.username && val.password) {
      this.authService.login(val.username, val.password).subscribe({
        next: (res: ILoginResponseDto) => {
          if (res.bearerToken) {
            this.sessionStorageService.save(HttpConstants.AUTHORIZATION, res.bearerToken);
            return this.router.navigateByUrl('dashboard');
          }
          console.log("Auth token not found after login!");
          return null;
        },
        error: (err) => {
          let errMsg = err.error ? err.error.message : err.message;
          console.log(errMsg);
        }
      });
    }
  }
}
