import { Injectable } from '@angular/core';
import { AppConst } from '../constants/app-constant';
import { Http, Headers } from '@angular/http';
import { UserShipping } from '../models/user-shipping';

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

  private servePath = AppConst.servePath;
  constructor( private http : Http) { }

  newShipping(shipping : UserShipping) {
    let url = this.servePath+'/shipping/add';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.post(url, JSON.stringify(shipping), {headers : tokenHeader});
  }

  getUserShippingList() {
    let url = this.servePath+'/shipping/getUserShippingList';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.get(url, {headers : tokenHeader});
  }

  removeShipping(id : number) {
    let url = this.servePath+'/shipping/remove';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.post(url, id, {headers : tokenHeader});
  }

  setDefaultShipping(id : number) {
    let url = this.servePath+'/shipping/setDefault';

    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.post(url, id, {headers : tokenHeader});
  }
}
