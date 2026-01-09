import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import {LoginRequest, UserService} from "../../../../api-client/generated-sources/shop-app-api";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginData: LoginRequest = {
    email: '',
    password: '',
  };
  error: string | null = null;

  constructor(private userService: UserService, private authService: AuthService, private router: Router) {}

  login(): void {
    this.userService.login(this.loginData).subscribe({
      next: (response) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/products']);
      },
      error: (err) => {
        this.error = 'Login failed';
        console.error(err);
      },
    });
  }
}
