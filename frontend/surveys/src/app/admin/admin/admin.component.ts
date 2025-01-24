import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarMenuComponent } from '../../components/sidebar-menu/sidebar-menu.component';
import { AuthenticationService } from '../../services/auth/authentication.service';

@Component({
    selector: 'app-admin',
    imports: [SidebarMenuComponent, RouterOutlet],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css'
})
export class AdminComponent implements OnInit {

    session: boolean | undefined = false;
    router = inject(Router);

    constructor(public authService: AuthenticationService) {
    }

    async ngOnInit(): Promise<void> {
        this.authService.startAuthIntervalVerifier();
    }

}
