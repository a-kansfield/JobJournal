import { Component } from '@angular/core';
import { ApplicationDataService } from '../data/service/application/application-data.service';
import { Application } from '../data/model/application/application';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-applications',
  imports: [NgFor],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.css'
})
export class ApplicationsComponent {
  applications! : Application[];
  constructor(
    private applicationService : ApplicationDataService,
  ) {}

  ngOnInit(){
    this.applicationService.retrieveByUserId(1).subscribe(
      response => {
        console.log(response);
        this.applications = response;
      }
    )
  }

  searchApplications(){
    
  }
  
}
