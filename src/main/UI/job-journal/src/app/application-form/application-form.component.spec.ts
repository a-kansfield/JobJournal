import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationFormComponent } from './application-form.component';
import { Application } from '../data/model/application/application';

describe('ApplicationFormComponent', () => {
  let component: ApplicationFormComponent;
  let fixture: ComponentFixture<ApplicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplicationFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApplicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should nullify one date value', () => {
    let application = new Application();
    application.dateDue = new Date('2025-05-23');
    component.nullifyDates(application)
    expect(application.dateApplied != null && application.dateDue == null).toBeTruthy
  })
});

