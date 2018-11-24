import { Injectable } from '@angular/core';
import { AppConst } from '../constants/app-constant';
import { Http, Headers } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private servePath = AppConst.servePath;
  constructor(private http : Http) { }

  addItem( id : number, qty : number) {
    let url = this.servePath+'/cart/add';

    let cartItemInfo = {
      'bookId' : id,
      'qty' : qty
    }
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url,cartItemInfo, {headers : tokenHeader})
  }

  getCartItemList() {
    let url = this.servePath+'/cart/getCartItemList';

    let tokenHeader  = new Headers( {
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  getShoppingCart() {
    let url = this.servePath+'/cart/getShoppingCart';

    let tokenHeader  = new Headers( {
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers : tokenHeader});
  }

  updateCartItem(cartItemId : number, qty : number) {
    let url = this.servePath+'/cart/updateCartItem';

    let cartItemInfo = {
      'cartItemId' : cartItemId,
      'qty' : qty
    }
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url,cartItemInfo, {headers : tokenHeader})
  }

  removeCartItem(id : number) {
    let url = this.servePath+'/cart/removeItem';
    let tokenHeader = new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, id, {headers : tokenHeader})
  }
}
