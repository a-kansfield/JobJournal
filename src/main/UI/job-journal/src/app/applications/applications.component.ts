import { Component } from '@angular/core';
import { ApplicationDataService } from '../data/service/application/application-data.service';
import { Application } from '../data/model/application/application';
import { NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-applications',
  imports: [RouterModule, RouterLink, NgFor, FormsModule],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.css'
})
export class ApplicationsComponent {
  applications! : Application[];
  query: string = '';
  userID : number = 1;
  constructor(
    private applicationService : ApplicationDataService,
    private router : Router
  ) {}

  ngOnInit(){
    this.refreshApplications();
  }

  refreshApplications(){
    this.applicationService.retrieveByUserId(this.userID).subscribe(
      response => {
        console.log(response);
        this.applications = response;
      }
  }


  searchApplications(){
    this.applicationService.retrieveBySearch(this.userID, this.query).subscribe(
      response => {
        console.log(response);
        console.log(this.query)
        
        this.applications = response;
      }
    )
  }

  deleteApplication(appID : number) {
    console.log("click");
    this.applicationService.deleteApplication(this.userID, appID).subscribe(
      response => {
        console.log(response);
        this.refreshApplications();
      }
    );

  }
  
}
