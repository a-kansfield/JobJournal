import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { map } from 'rxjs/operators';
import { User } from '../../data/model/user/user';
import { UserDataService } from '../../data/service/user/user-data.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private springDomain! : string;
  constructor(
    private http : HttpClient,
    private userService : UserDataService,
    private router : Router,
  ) { 
    this.springDomain = environment.springDomain;
  }

  authenticate(email : string, password : string) {
  
    if (this.userService.retrieveUser(email, password)) {
      
    };
    if (true) {

      sessionStorage.setItem('authenticatedUser', email);
      return true;
    }
    return false;
    
  }

  executeAuthService(email : string, password : string){

    let requestBody = {email, password};
    let authString = 'Basic ' + window.btoa('user' + ":" + 'password');

    let headers = new HttpHeaders({
      Authorization: authString
    })

    return this.http.post<AuthenticationBean>(`${this.springDomain}/basic-auth`, requestBody, {headers}).pipe(
      
      map(
        data => {
            console.log(data.id);
            sessionStorage.setItem('authenticatedUser', email);
            sessionStorage.setItem('token', authString);
            sessionStorage.setItem('id', data.id.toString());
          
          return data;
        }
      )
    )
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem('authenticatedUser');
  }
  
  getAuthenticatedToken() {
    if(this.getAuthenticatedUser()) {
      return sessionStorage.getItem('token');
    } else { return null }
    
    
  }
  isUserLoggedIn() {
    let user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('id');
    this.router.navigate(['']);
  }
  
}

export class AuthenticationBean {

  constructor(public id:number) {

  }
}