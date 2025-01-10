import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarMenuComponent } from '../../components/sidebar-menu/sidebar-menu.component';

@Component({
    selector: 'app-admin',
    imports: [SidebarMenuComponent, RouterOutlet],
    templateUrl: './admin.component.html',
    styleUrl: './admin.component.css'
})
export class AdminComponent {

}
