import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../authentication/auth.service';
@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(
    public authService : AuthService,
    private router : Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.getAuthenticatedUser()){
      return true;
    } else {
      this.router.navigate(['login']);
      return false;
    }
  }
}
