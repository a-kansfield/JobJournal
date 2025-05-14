import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { User } from '../data/model/user/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDataService } from '../data/service/user/user-data.service';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from '../service/authentication/auth.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-sign-up',
  imports: [FormsModule, NgIf],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})

export class SignUpComponent {
  user! : User;
  err : boolean = false;
  errorMsg : string = 'Email already in use.'
  constructor(
    private userService : UserDataService,
    private route : ActivatedRoute,
    private router : Router,
    private authService : AuthService
  ){}

  ngOnInit(){
    this.user = new User(-1, '', '', '', '');
  }

  saveUser() {

    this.userService.saveUser(this.user).subscribe(
      response => {
        console.log("User saved");
        this.authService.executeAuthService(this.user.email, this.user.password).subscribe(
          data => {
            this.err = false;
            this.router.navigate(['applications']);
          }
        )

      },
      error => {
        this.err = true;
      }
    )
  }


}
