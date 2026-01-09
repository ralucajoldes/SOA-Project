import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../../services/auth.service";
import {RegisterRequest, UserService} from "../../../../api-client/generated-sources/shop-app-api";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  registerData: RegisterRequest = {
    email: '',
    password: '',
    name: '',
  };
  error: string | null = null;

  constructor(private userService: UserService, private authService: AuthService, private router: Router) {}

  register(): void {
    this.userService.register(this.registerData).subscribe({
      next: (response) => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/products']);
      },
      error: (err) => {
        this.error = 'Registration failed';
        console.error(err);
      },
    });
  }
}
