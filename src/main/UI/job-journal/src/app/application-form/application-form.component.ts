import { Component, ChangeDetectionStrategy, AfterViewInit, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Status } from '../data/model/status/status';
import { StatusDataService } from '../data/service/status/status-data.service';
import { AsyncPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { Application } from '../data/model/application/application';
import { ActivatedRoute, Router } from '@angular/router';
import { ApplicationDataService } from '../data/service/application/application-data.service';
import { User } from '../data/model/user/user';
import { UserDataService } from '../data/service/user/user-data.service';

@Component({
  selector: 'app-application-form',
  imports: [FormsModule, NgFor, NgIf, DatePipe ],
  templateUrl: './application-form.component.html',
  styleUrl: './application-form.component.css',
})
export class ApplicationFormComponent implements OnInit, AfterViewInit{

  statuses! : Status[];
  application! : Application;
  applicationSent: boolean = false;
  id!: number;
  tempDate : Date = new Date(Date.now());
  currentDateString! : string;

  constructor(
    private statusService : StatusDataService,
    private applicationService : ApplicationDataService,
    private userService : UserDataService,
    private route : ActivatedRoute,
    private router : Router,
    private changeDetection: ChangeDetectorRef
    
  ){
    //this.statuses = this.statusService.statusList
  }
  
  ngOnInit(){
    this.id = this.route.snapshot.params['id'];
    
    this.statusService.retrieveStatusList().subscribe(
      response => {
        console.log(response);
        this.statuses = response;
        
        this.application = new Application(
          this.id,
          this.tempDate,
          this.tempDate,
          '',
          '',
    
          null,
          null,
          null,
          this.statuses[2],
          1
        );

        if(this.id!=-1){
          this.applicationService.retrieveById(this.application.id).subscribe(
            data => {
              this.application = data
            }
          )
        }
        this.changeDetection.detectChanges();
        
      }

    );
    this.getCurrentDate();
  }
  ngAfterViewInit(){
    
  }
  ngOnChanges() {

  }

  getCurrentDate() {
    let date = new Date(Date.now());

    let dateString : string = ''
    dateString = `${date.getFullYear}-${date.getMonth}-${date.getDate}`
    console.log(this.currentDateString)
    this.currentDateString = dateString;
    return dateString

    //console.log(this.tempDate.toLocaleDateString());
  }

  printApplicationType(event : any){
    
    this.applicationSent = event.target.checked;
    console.log(this.applicationSent === true);
  }

  saveApplication(){
    console.log("Application Sent: " + this.applicationSent)
    if (this.applicationSent === true) {
        this.application.dateDue = null;
    } else if (this.applicationSent === false){
        this.application.dateApplied = null;
    }

    this.applicationService.createApplication(this.application.userID, this.application).subscribe(
      data => {
        this.router.navigate(['applications']);
      }
    );
  }
}
