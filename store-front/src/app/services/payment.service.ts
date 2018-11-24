import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { AppConst } from '../constants/app-constant';
import { UserPayment } from '../models/user-payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private servePath : string = AppConst.servePath;

  constructor(private http : Http) { }

  newPayment(payment : UserPayment) {
    let url = this.servePath+'/payment/add';

    let tokenHeader =  new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, JSON.stringify(payment), {headers : tokenHeader})
  }

  getUserPaymentList() {
    let url = this.servePath+'/payment/getUserPaymentList';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.get(url, {headers :tokenHeader})
  }

  removePayment(id : number) {
    let url = this.servePath+'/payment/remove';

    let tokenHeader =  new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, id, {headers : tokenHeader})
  }

  setDefaultPayment(id : number) {
    let url = this.servePath+'/payment/setDefault';

    let tokenHeader =  new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, id, {headers : tokenHeader})
  }
}
