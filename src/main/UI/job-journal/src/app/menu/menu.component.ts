import { AfterViewInit, Component, ComponentFactoryResolver, ElementRef, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { UserDataService } from '../data/service/user/user-data.service';
import { Router, RouterLink } from '@angular/router';
import { User } from '../data/model/user/user';
import { NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-menu',
  imports: [RouterLink, NgFor, NgIf],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent{
  // Preload components
  loginComponent = LoginComponent;

  user? : User;
  loginVisible : boolean = false;
  constructor(
    private userService : UserDataService,
    private router : Router,
    private viewContainer : ViewContainerRef
  ) {}


toggleView(toggle : boolean, component : any) {
  
  if (toggle) {
    this.viewContainer.clear();
  } else {
    this.viewContainer.createComponent(component);
  }

if (component === this.loginComponent) {
  toggle = !toggle;
  this.loginVisible = toggle;
}

  
  
}




}

