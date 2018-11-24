import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { UserService } from '../services/user.service';
import {AppConst} from '../constants/app-constant';
import { Router } from '@angular/router';


@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  private servePath = AppConst.servePath;
  private loginError : boolean = false;
  private loggedIn = false;
  private credential = { "username" : "", "password" : '' };
  
  private emailSent : boolean = false;
  private usernameExists : boolean;
  private emailExists : boolean;
  private email :  string;
  private username : string;

  private emailNotExists : boolean = false;
  private forgetPasswordEmailSent : boolean = false;
  private recoverEmail : string;

  constructor(
    private loginService : LoginService,
    private userService : UserService,
    private router : Router
  ) { }

  onLogin() {
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      res => {
        console.log(res);
        localStorage.setItem('xAuthToken', res.json().token);
        this.loggedIn = true;
        console.log(this.loggedIn)
        location.reload();
        this.router.navigate(['/home']);
      },
      error => {
        console.log(error);
        this.loggedIn = false;
        this.loginError = true;
      }
    );
  }

  onNewAccount(){
    this.usernameExists= false;
    this.emailExists = false;
    this.emailSent = false;

    this.userService.newUser(this.username, this.email).subscribe(
      res => {
        console.log(res);
        this.emailSent = true;
      },
      error => {
        console.log(error.text());
        let errorMessage = error.text();
        if(errorMessage === "usernameExists" ) this.usernameExists = true;
        if(errorMessage === "emailExists") this.emailExists = true;
      });
  }

  onForgetPassword() {
    this.forgetPasswordEmailSent =  false;
    this.emailNotExists = false;
    console.log(this.recoverEmail);
    this.userService.retrievePassword(this.recoverEmail).subscribe(
      res => {
        console.log(res);
        this.forgetPasswordEmailSent = true;
      },
      error => {
        console.log(error.text());
        let errorMessage = error.text();
        if(errorMessage === "Email not found") this.emailNotExists = true;


      });
  }
  ngOnInit() {
  	this.loginService.checkSession().subscribe(
  		res => {
        console.log(res)
  			this.loggedIn = true;
  		},
  		error => {
  			this.loggedIn = false;
  		}
  	);
  }

}
