import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from "./menu/menu.component";
import { LoginComponent } from "./login/login.component";
import { ApplicationsComponent } from "./applications/applications.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MenuComponent, LoginComponent, ApplicationsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'job-journal';
}
