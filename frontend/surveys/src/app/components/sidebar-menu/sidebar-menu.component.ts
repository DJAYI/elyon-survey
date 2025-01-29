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
    currentTab: number;
    toggleMenu = true;
    router = inject(Router);

    constructor(public http: HttpClient, public authService: AuthenticationService) {
        this.currentTab = parseInt(localStorage.getItem('currentTab') || '0');

        if (this.currentTab === 0) {
            this.router.navigate(['/admin']);
        } else if (this.currentTab === 1) {
            this.router.navigate(['/admin/analytics']);
        } else if (this.currentTab === 2) {
            this.router.navigate(['/admin/surveys']);
        } else if (this.currentTab === 3) {
            this.router.navigate(['/admin/users']);
        }

        this.toggleMenu = localStorage.getItem('toggleMenu') === 'true';
    }
    handleCurrentTab(tab: number) {
        this.currentTab = tab;

        localStorage.setItem('currentTab', tab.toString());
        if (tab === 0) {
            this.router.navigate(['/admin']);
        } else if (tab === 1) {
            this.router.navigate(['/admin/analytics']);
        } else if (tab === 2) {
            this.router.navigate(['/admin/surveys']);
        } else if (tab === 3) {
            this.router.navigate(['/admin/users']);
        }
    }

    isTabActive(tab: number): boolean {

        return this.currentTab === tab
    }

    logout() {
        this.authService.logout();
    }

    toggleSidebar() {
        this.toggleMenu = !this.toggleMenu;
        localStorage.setItem('toggleMenu', this.toggleMenu.toString());
    }
}

