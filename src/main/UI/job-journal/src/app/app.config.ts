import { FormsModule } from '@angular/forms';

import { BrowserModule } from '@angular/platform-browser';

import { HttpIntercepterService } from './service/http/http-intercepter.service';

import {  
  ApplicationConfig, 
  provideZoneChangeDetection, 
  importProvidersFrom } from '@angular/core';

import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

import { 
  HTTP_INTERCEPTORS,
  provideHttpClient, 
  withInterceptorsFromDi } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    importProvidersFrom(
      BrowserModule, 
      FormsModule), 
    provideRouter(routes),
     { provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterService, multi: true }, // ADD THIS
    provideHttpClient(withInterceptorsFromDi()),
  ]
};
