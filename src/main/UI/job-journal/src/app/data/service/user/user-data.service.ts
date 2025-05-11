import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
    return this.http.get<User>(`${this.springDomain}${this.endpoint}/user`);
  }

  saveUser(user : User){
    return this.http.post(`${this.springDomain}${this.endpoint}/new`, user);
  }

}