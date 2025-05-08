import { Component } from '@angular/core';
import { ApplicationDataService } from '../data/service/application/application-data.service';

@Component({
  selector: 'app-applications',
  imports: [],
  templateUrl: './applications.component.html',
  styleUrl: './applications.component.css'
})
export class ApplicationsComponent {
  constructor(
    private applicationService : ApplicationDataService,
  ) {}


  // applicationService.
}
