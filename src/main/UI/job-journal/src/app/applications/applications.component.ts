import { Component } from '@angular/core';
import { ApplicationDataService } from '../data/service/application/application-data.service';
import { Application } from '../data/model/application/application';
import { NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-applications',
  imports: [NgFor, FormsModule],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.css'
})
export class ApplicationsComponent {
  applications! : Application[];
  query: string = ''
  constructor(
    private applicationService : ApplicationDataService,
  ) {}

  ngOnInit(){
    this.applicationService.retrieveByUserId(1).subscribe(
      response => {
        console.log(response);
        this.applications = response;
      }
    );
  }

  // refreshApplications(){
  //   this.applicationService.retrieveByUserId(1).subscribe(
  //     response => {
  //       console.log(response);
  //       this.applications = response;
  //     }
  // }


  searchApplications(){
    this.applicationService.retrieveBySearch(1, this.query).subscribe(
      response => {
        console.log(response);
        console.log(this.query)
        
        this.applications = response;
      }
    )
  }
  
}
