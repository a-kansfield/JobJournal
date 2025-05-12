import { AfterViewInit, Component, ComponentFactoryResolver, ElementRef, inject, ViewChild, ViewContainerRef } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { UserDataService } from '../data/service/user/user-data.service';
import { Router, RouterLink } from '@angular/router';
import { User } from '../data/model/user/user';
import { NgFor, NgIf } from '@angular/common';
import { ApplicationDataService } from '../data/service/application/application-data.service';
import { AuthService } from '../service/authentication/auth.service';

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
  userID : number = 1;
  constructor(
    private userService : UserDataService,
    private applicationService : ApplicationDataService,
    private router : Router,
    public authService : AuthService
  ) {}

  // toggleView(toggle : boolean, component : any) {
    
  //   if (toggle) {
  //     this.viewContainer.clear();
  //   } else {
  //     this.viewContainer.createComponent(component);
  //   }

  //   if (component === this.loginComponent) {
  //     toggle = !toggle;
  //     this.loginVisible = toggle;
  //   }
  // }

  downloadFile() {
    this.applicationService.downloadFile(this.userID).subscribe(
      response => {
        let blob: Blob = response.body as Blob;
        const url = URL.createObjectURL(blob);

        let ele = document.createElement('a');

        ele.href = url;
        ele.download = 'Applications.csv';

        
        ele.click();
        console.log(response);
      }
    )
  }

  
}

