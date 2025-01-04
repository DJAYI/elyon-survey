import { Routes } from '@angular/router';
import { AdminComponent } from './admin/admin/admin.component';
import { AnalyticsComponent } from './admin/analytics/analytics.component';
import { AuthComponent } from './admin/auth/auth.component';
import { LoginComponent } from './admin/auth/login/login.component';
import { SurveysComponent } from './admin/surveys/surveys.component';
import { UsersComponent } from './admin/users/users.component';
import { StepperComponent } from './components/stepper/stepper.component';
import { SurveyItemComponent } from './components/survey-item/survey-item.component';
import { authGuard } from './guards/auth/auth.guard';
import { roleGuard } from './guards/role/role.guard';

export const routes: Routes = [
  {
    path: '',
    component: StepperComponent
  },
  {
    path: 'auth',
    component: AuthComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [authGuard],
    children: [
      {
        path: 'users',
        component: UsersComponent,
        canActivate: [roleGuard],
      },
      {
        path: 'analytics',
        component: AnalyticsComponent
      },
      {
        path: 'surveys',
        component: SurveysComponent,
        children: [
          {
            path: ':id',
            component: SurveyItemComponent
          }
        ]
      }
    ]
  }
];
