import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Application } from '../../model/application/application';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApplicationDataService {
  private endpoint : string = '/applications'
  private springDomain! : string;
  constructor(
    private http : HttpClient
  ) { 
    this.springDomain = environment.springDomain;
  }

  retrieveByUserId(id : number) {
    console.log(this.springDomain);
    return this.http.get<Application[]>(`${this.springDomain}${this.endpoint}/all/user-${id}`);
  }

  retrieveByStatus(userID : number, statusID : number){
    return this.http.get<Application[]>(`${this.springDomain}${this.endpoint}/all/user-${userID}/status-${statusID}`);
  }

  retrieveBySearch(userID : number, query : string){
    return this.http.get<Application[]>(`${this.springDomain}${this.endpoint}/user-${userID}/search?query=${query}`);
  }
}
