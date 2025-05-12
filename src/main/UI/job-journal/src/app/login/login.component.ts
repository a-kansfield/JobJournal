import { Component, ElementRef, ViewChild, ViewContainerRef, OnDestroy  } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { AuthService } from '../service/authentication/auth.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink, NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @ViewChild('outerDiv') outerDiv!: ElementRef;
  email : string = '';
  password : string = '';
  invalidLogin : boolean = false;
  errorMsg : string = 'Invalid Login'
  constructor(
    private authService : AuthService,
    private router : Router,
  ) {} 
  
  authLogin(){
    this.authService.executeAuthService(this.email, this.password).subscribe(
      data => {
        console.log(data);
        this.invalidLogin = false;
        
        this.router.navigate([`applications`]);
      },
      error => {
        this.invalidLogin = true;
        console.log(error);
      }

    );
  }
  // loadContent() {    
  //   this.viewContainer.createComponent(LoginComponent);  
  // }
}
