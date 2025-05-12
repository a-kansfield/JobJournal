import { Routes } from '@angular/router';
import { ApplicationsComponent } from './applications/applications.component';
import { ApplicationFormComponent } from './application-form/application-form.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RouteGuardService } from './service/route-guard/route-guard.service';
export const routes: Routes = [

    // {path: '', component: HomeComponent},
    { path: '', component: HomeComponent, pathMatch: 'full'},
    { path: 'login', component: LoginComponent},
    {path: ':userID/application/edit/:id', component: ApplicationFormComponent, canActivate:[RouteGuardService]},
    {path: 'applications', component: ApplicationsComponent, canActivate:[RouteGuardService]},
    {path: 'user/sign-up', component: SignUpComponent}
];
