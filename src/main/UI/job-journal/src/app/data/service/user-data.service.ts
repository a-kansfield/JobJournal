import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user/user'

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(
    private http : HttpClient
  ) { }

  retrieveAllUsers() {
    //return this.http.get<User[]>(`http://localhost:8080/`)
    return this.http.get<User[]>(`https://job-journal-206c28e002ca.herokuapp.com/`)
  }

}

