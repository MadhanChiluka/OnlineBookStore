import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { AppConst } from '../constants/app-constant';
import { UserShipping } from '../models/user-shipping';
import { UserBilling } from '../models/user-billing';
import { UserPayment } from '../models/user-payment';
import { ShippingAddress } from '../models/shipping-address';
import { BillingAddress } from '../models/billing-address';
import { Payment } from '../models/payment';

@Injectable({
  providedIn: 'root'
})
export class CheckOutService {

  private servePath = AppConst.servePath;

  constructor(private http : Http) { }

  checkout(
    shippingAddress : ShippingAddress,
    billingAddress : BillingAddress,
    payment : Payment,
    shippingMethod : string
  ) {
    let url = this.servePath+"/checkout/checkout";
    let order = {
    "shippingAddress" : shippingAddress,
    "billingAddress" : billingAddress,
    "payment" : payment,
    "shippingMethod" : shippingMethod
    }
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })

    return this.http.post(url, order, {headers : tokenHeader});

  }

  getUserOrder() {
    let url = this.servePath+'/order/getUserOrder';
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.get(url , {headers : tokenHeader});
  }
}
