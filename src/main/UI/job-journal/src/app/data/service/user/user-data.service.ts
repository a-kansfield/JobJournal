import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../../model/user/user'
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  private endpoint : string = '/users'
  private springDomain! : string;
  constructor(
    private http : HttpClient
  ) {
    this.springDomain = environment.springDomain;
   }

  retrieveAllUsers() {
    //return this.http.get<User[]>(`http://localhost:8080/`)
    return this.http.get<User[]>(`${this.springDomain}${this.endpoint}/all`);
  }

  retrieveUser(email: string, password: string){
    return this.http.get<User>(`${this.springDomain}${this.endpoint}/validate-user`);
  }

  saveUser(user : User){
    const authString = this.createAuthenticationHeader();
    const headers = new HttpHeaders({
      Authorization: authString
    })
    return this.http.post(`${this.springDomain}${this.endpoint}/new`, user, {headers});
  }

  createAuthenticationHeader() {
      let username = 'user';
      let password = 'password';
      let authString = 'Basic ' + window.btoa(username + ":" + password);

      return authString;
  }

}