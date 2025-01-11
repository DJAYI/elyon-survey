import { Routes } from '@angular/router';
import { AdminComponent } from './admin/admin/admin.component';
import { AnalyticsComponent } from './admin/analytics/analytics.component';
import { AuthComponent } from './admin/auth/auth.component';
import { SurveysComponent } from './admin/surveys/surveys.component';
import { UsersComponent } from './admin/users/users.component';
import { StepperComponent } from './components/stepper/stepper.component';
import { SurveyItemComponent } from './components/survey-item/survey-item.component';
import { adminGuard } from './guards/admin/admin.guard';
import { authGuard } from './guards/auth/auth.guard';
import { loginGuard } from './guards/auth/login/login.guard';
import { roleGuard } from './guards/role/role.guard';

export const routes: Routes = [
  {
    path: '',
    component: StepperComponent
  },
  {
    path: 'auth',
    component: AuthComponent,
    canActivate: [loginGuard],
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [authGuard],
    canActivateChild: [adminGuard],
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
