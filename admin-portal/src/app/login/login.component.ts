import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

private credential = {"username":"", "password" : ""}
private loggedIn = false;
  constructor(private loginService : LoginService, private router : Router) {
    
   }
  
  onSubmit() {
    this.loginService.sendCredential(this.credential.username, this.credential.password)
	.subscribe(
	res => {
    this.loggedIn = true;
    console.log(res);
		localStorage.setItem("xAuthToken", res.json().token);
    location.reload();
    this.router.navigate(['/home'])
	},
	error => {
		console.log(error);  	
  }
  );
  
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
