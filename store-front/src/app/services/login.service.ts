import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { AppConst } from '../constants/app-constant';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private servePath = AppConst.servePath;

  constructor(private http : Http, private router :  Router) { }

  sendCredential(username : string, password : string) {
    let url = this.servePath+'/token';
    let encodedCredentials = btoa(username+":"+password);
    let basicHeader = "Basic "+encodedCredentials;
    let headers = new Headers({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization' : basicHeader
    });
    return this.http.get(url, {headers : headers});
  }

  checkSession() {
    let url = this.servePath+'/checkSession';
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers : headers});
  }

  logout() {
    let url =  this.servePath+'/user/logout';
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, '', {headers : headers});
  }
}
