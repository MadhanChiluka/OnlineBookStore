import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private loggedIn = false;
  constructor(private loginService : LoginService, private router : Router) {  }
  toggleDisplay() {
    this.loggedIn = !this.loggedIn;
    }

  logout() {
    this.loginService.logout().subscribe(
      res =>{
        console.log(res);
        this.loggedIn = false;
        this.router.navigate(['/'])
      },
      error => {
        console.log(error);
      }
    )
  }
  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        console.log(res)
        this.loggedIn = true;
    },
    error => {
      console.log(error);
      this.loggedIn = false;
    })
  }
}
