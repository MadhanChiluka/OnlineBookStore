import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { AppConst } from '../constants/app-constant';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private servePath = AppConst.servePath;
  constructor(private http : Http ) { }

  getOrderList() {
    let url = this.servePath+'order/getOrderList';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.get(url, {headers : tokenHeader});
  }
}
