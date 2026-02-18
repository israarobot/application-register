import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';

@Component({
    selector: 'app-user-list',
    standalone: true,
    imports: [CommonModule, RouterModule],
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
    users: User[] = [];
    errorMessage: string = '';

    constructor(private userService: UserService) { }

    ngOnInit(): void {
        this.loadUsers();
    }

    loadUsers(): void {
        this.userService.getUsers().subscribe({
            next: (data) => this.users = data,
            error: (err) => this.errorMessage = 'Could not load users'
        });
    }

    deleteUser(id: number): void {
        if (confirm('Are you sure you want to delete this user?')) {
            this.userService.deleteUser(id).subscribe({
                next: () => {
                    this.users = this.users.filter(u => u.id !== id);
                },
                error: (err) => alert('Failed to delete user')
            });
        }
    }
}
