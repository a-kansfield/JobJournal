import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Status } from '../../model/status/status';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StatusDataService {
  private endpoint : string = '/status'
  private springDomain! : string;
  public statusList! : Status[];
  constructor(
    private http : HttpClient
  ) { 
    this.springDomain = environment.springDomain;
    this.http.get<Status[]>(`${this.springDomain}${this.endpoint}/all`).subscribe(response => {
      this.statusList = response;
    })
  }

  retrieveStatusList() {
    
    return this.http.get<Status[]>(`${this.springDomain}${this.endpoint}/all`);
  }
}
