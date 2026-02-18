import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
    selector: 'app-user-details',
    standalone: true,
    imports: [CommonModule, ReactiveFormsModule, RouterModule],
    templateUrl: './user-details.component.html',
    styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
    userForm: FormGroup;
    userId: number | null = null;
    errorMessage: string = '';
    successMessage: string = '';

    constructor(
        private fb: FormBuilder,
        private userService: UserService,
        private route: ActivatedRoute,
        private router: Router
    ) {
        this.userForm = this.fb.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: [''] // Optional on edit
        });
    }

    ngOnInit(): void {
        const id = this.route.snapshot.paramMap.get('id');
        if (id) {
            this.userId = +id;
            this.userService.getUser(this.userId).subscribe({
                next: (user) => {
                    this.userForm.patchValue({
                        firstName: user.firstName,
                        lastName: user.lastName,
                        email: user.email
                    });
                },
                error: (err) => {
                    this.errorMessage = 'Could not load user details';
                }
            });
        }
    }

    onSubmit(): void {
        if (this.userForm.valid && this.userId) {
            const formValue = this.userForm.value;
            // If password is empty, don't send it (or handle in backend, my backend handles null password check)
            if (!formValue.password) {
                delete formValue.password;
            }

            this.userService.updateUser(this.userId, formValue).subscribe({
                next: () => {
                    this.successMessage = 'User updated successfully';
                    setTimeout(() => this.router.navigate(['/users']), 1500);
                },
                error: (err) => {
                    this.errorMessage = 'Failed to update user';
                }
            });
        }
    }
}
