import { NgClass } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { lastValueFrom } from 'rxjs';

@Component({
    selector: 'app-sidebar-menu',
    imports: [NgClass, RouterLink],
    templateUrl: './sidebar-menu.component.html',
    styleUrl: './sidebar-menu.component.css'
})
export class SidebarMenuComponent {
    currentTab = 0;
    toggleMenu = false;

    constructor(public http: HttpClient, public router: Router) { }
    handleCurrentTab(tab: number) {
        this.currentTab = tab;
    }

    isTabActive(tab: number): boolean {
        return this.currentTab === tab;
    }

    logout() {
        // Logout logic here
        lastValueFrom(this.http.post('http://localhost:8080/api/v1/auth/logout', {}, {
            withCredentials: true
        })).then(() => {
            this.router.navigate(['/auth/login']);
        }).catch(e => {
            console.log("Error: " + e);
        });
    }

    toggleSidebar() {
        this.toggleMenu = !this.toggleMenu;
    }
}

