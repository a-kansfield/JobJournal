import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../authentication/auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterService implements HttpInterceptor{

  constructor(
    private authService : AuthService
  ) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // let email = 'user';
    // let password = 'password';
    // let authString = 'Basic ' + window.btoa(email + ":" + password);

    let authString = this.authService.getAuthenticatedToken();
    let email = this.authService.getAuthenticatedUser()

    if (authString && email){
      request = request.clone({
        setHeaders: {
          Authorization: authString
        }
      })
    }


    return next.handle(request);
  }
}
