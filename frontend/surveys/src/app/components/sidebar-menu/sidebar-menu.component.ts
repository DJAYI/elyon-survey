import { NgClass } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthenticationService } from '../../services/auth/authentication.service';

@Component({
    selector: 'app-sidebar-menu',
    imports: [NgClass, RouterLink],
    templateUrl: './sidebar-menu.component.html',
    styleUrl: './sidebar-menu.component.css'
})
export class SidebarMenuComponent {
    currentTab = 0;
    toggleMenu = false;
    router = inject(Router);

    constructor(public http: HttpClient, public authService: AuthenticationService) { }
    handleCurrentTab(tab: number) {
        this.currentTab = tab;
    }

    isTabActive(tab: number): boolean {
        return this.currentTab === tab;
    }

    logout() {
        this.authService.logout();
    }

    toggleSidebar() {
        this.toggleMenu = !this.toggleMenu;
    }
}

