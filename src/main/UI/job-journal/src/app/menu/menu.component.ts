import { Component } from '@angular/core';
import { UserDataService } from '../data/service/user-data.service';
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

  message : string = "This message is being input programmatically";
  users? : User[];
  constructor(
    private userService : UserDataService,
    private router : Router
  ) {}

  ngOnInit() {
    this.userService.retrieveAllUsers().subscribe(
      data => { 
        console.log(data)
        this.users = data}

    )
  }
}
