import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private isLoggedIn = false;
  constructor(
    private loginService : LoginService, 
    private router : Router) { }

    logout() {
      this.loginService.logout().subscribe(
        res => {
          location.reload();
        },
        err => {
          console.log(err);
        }
      );
    }
  
  

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        console.log(res)
        this.isLoggedIn = true;
        
      },
      error => {
        console.log(error)
        this.isLoggedIn = false;
    });
  }

}
