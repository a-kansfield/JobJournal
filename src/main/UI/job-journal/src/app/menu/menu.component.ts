import { Component } from '@angular/core';
import { UserDataService } from '../data/service/user/user-data.service';
import { Router } from '@angular/router';
import { User } from '../data/model/user/user';
import { NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-menu',
  imports: [NgFor, NgIf],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {


  user? : User;
  constructor(
    private userService : UserDataService,
    private router : Router
  ) {}

}
