import { Routes } from '@angular/router';
import { ApplicationsComponent } from './applications/applications.component';
import { ApplicationFormComponent } from './application-form/application-form.component';
import { SignUpComponent } from './sign-up/sign-up.component';
export const routes: Routes = [

    {path: ':userID/application/edit/:id', component: ApplicationFormComponent},
    {path: 'applications', component: ApplicationsComponent},
    {path: 'user/sign-up', component: SignUpComponent}
];
