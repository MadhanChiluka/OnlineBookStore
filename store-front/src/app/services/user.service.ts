import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { User } from '../models/user';
import { AppConst } from '../constants/app-constant';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private servePath = AppConst.servePath;

  constructor(private http : Http) { }

  newUser(username : string, email : string) {
    let url = this.servePath+'/user/newUser';
    let userInfo = {
      'username' : username,
      'email' : email
    };
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  retrievePassword(email : string) {
    let url = this.servePath+'/user/forgetPassword';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, email, {headers : tokenHeader});
  }

  updateUserInfo(user : User, newPassword : string, currentPassword : string) {
    let url = this.servePath+'/user/updateUserInfo';

    let userInfo = {
      "id" : user.id,
      "firstName" : user.firstName,
      "lastName" : user.lastName,
      "username" : user.username,
      "currentPassword" : currentPassword,
      "email" : user.email,
      "newPassword" : newPassword
    }
     let tokenHeader = new Headers({
       'Content-Type' : 'application/json',
       'x-auth-token' : localStorage.getItem('xAuthToken')
     });

     return this.http.post(url, JSON.stringify(userInfo), {headers : tokenHeader});
  }

  getCurrentUser() {
    let url = this.servePath+'/user/getCurrentUser';
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers : tokenHeader});
  }
}
