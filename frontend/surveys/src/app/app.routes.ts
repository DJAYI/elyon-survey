import { Routes } from '@angular/router';
import { AdminComponent } from './admin/admin/admin.component';
import { AnalyticsComponent } from './admin/analytics/analytics.component';
import { AuthComponent } from './admin/auth/auth.component';
import { SurveysComponent } from './admin/surveys/surveys.component';
import { UsersComponent } from './admin/users/users.component';
import { AppComponent } from './app.component';

export const routes: Routes = [
  {
    path: '',
    component: AppComponent
  },
  {
    path: 'auth',
    component: AuthComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'analytics',
        component: AnalyticsComponent
      },
      {
        path: 'surveys',
        component: SurveysComponent
      }
    ]
  }
];
