import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarMenuComponent } from '../../components/sidebar-menu/sidebar-menu.component';
import { AuthenticationService } from '../../services/auth/authentication.service';

@Component({
    selector: 'app-admin',
    imports: [SidebarMenuComponent, RouterOutlet],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {
    constructor(public authService: AuthenticationService) { }

    ngOnInit(): void {
        setInterval(() => {
            this.authService.checkAuthenticated();
        }, 10000);
    }
}
