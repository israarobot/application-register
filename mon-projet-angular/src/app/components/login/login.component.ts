import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    loginForm: FormGroup;
    errorMessage: string = '';

    constructor(
        private fb: FormBuilder,
        private userService: UserService,
        private router: Router
    ) {
        this.loginForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    onSubmit(): void {
        if (this.loginForm.valid) {
            this.userService.login(this.loginForm.value).subscribe({
                next: (user) => {
                    // Store user details if needed, e.g. localStorage
                    localStorage.setItem('user', JSON.stringify(user));
                    this.router.navigate(['/users']);
                },
                error: (err) => {
                    this.errorMessage = err.error || 'Login failed';
                }
            });
        }
    }
}
