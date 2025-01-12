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

    session: any
    router = inject(Router);

    constructor(public authService: AuthenticationService) {
        this.authService.checkAuthenticated().then(session => this.session = session);
    }

    async ngOnInit(): Promise<void> {
        const sessionChecker = setInterval(async () => {
            this.session = await this.authService.checkAuthenticated();
            if (!this.session) {
                clearInterval(sessionChecker);

                this.router.navigate(['/auth']);
            }
        }, 10000);
    }

}
