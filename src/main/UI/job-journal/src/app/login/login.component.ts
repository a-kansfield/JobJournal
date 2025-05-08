import { Component, ElementRef, ViewChild, ViewContainerRef } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{
  @ViewChild('outerDiv') outerDiv!: ElementRef;
  x! : number;
  y! : number; 

  constructor(
    private viewContainer : ViewContainerRef
  ) {} 
  
  // loadContent() {    
  //   this.viewContainer.createComponent(LoginComponent);  
  // }
}
