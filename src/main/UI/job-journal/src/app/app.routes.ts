import { Routes } from '@angular/router';
import { ApplicationsComponent } from './applications/applications.component';
import { ApplicationFormComponent } from './application-form/application-form.component';
export const routes: Routes = [

    {path: 'application/new', component: ApplicationFormComponent},
    {path: 'applications', component: ApplicationsComponent}
];
