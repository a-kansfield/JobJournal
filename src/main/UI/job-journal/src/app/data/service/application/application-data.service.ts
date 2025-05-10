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
  
  retrieveById(appID : number) {
    return this.http.get<Application>(`${this.springDomain}${this.endpoint}/application-${appID}`)
  }

  retrieveByUserId(id : number) {
    return this.http.get<Application[]>(`${this.springDomain}${this.endpoint}/all/user-${id}`);
  }

  retrieveByStatus(userID : number, statusID : number){
    return this.http.get<Application[]>(`${this.springDomain}${this.endpoint}/all/user-${userID}/status-${statusID}`);
  }

  retrieveBySearch(userID : number, query : string){
    let requestURL = `${this.springDomain}${this.endpoint}/user-${userID}/search?query=${query}`
    
    return this.http.get<Application[]>(requestURL);

  }



  createApplication(userID : number, application : Application) {
    return this.http.post<Application>(`${this.springDomain}${this.endpoint}/user-${userID}/application-new`, application);
  }

  deleteApplication(userID : number, appID : number) {
        return this.http.delete<Application>(`${this.springDomain}${this.endpoint}/user-${userID}/application-${appID}`);
  }

  updateApplication(userID : number, appID : number, application : Application) {
    return this.http.put<Application>(`${this.springDomain}${this.endpoint}/user-${userID}/application-${appID}`, application);
  }

  downloadFile(userID: number) {
    return this.http.get(`${this.springDomain}${this.endpoint}/user-${userID}/download-applications`, {observe:'response', responseType: 'blob'});
  }
}