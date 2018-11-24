import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { AppConst } from '../constants/app-constant';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private servePath = AppConst.servePath;
  constructor(private http : Http) { }

  getBookList() {
    let url = this.servePath+'/book/bookList';

    let tokenHeader =  new  Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.get(url, {headers : tokenHeader}); 
  }

  getBook(id  : number) {
    let url = this.servePath+'/book/'+id;

    let tokenHeader =  new  Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.get(url, {headers : tokenHeader});
  }

  searchBook(keyword : string) {
    let url = this.servePath+"/book/searchBook";

    let tokenHeader =  new Headers({
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    })
    return this.http.post(url, keyword, {headers : tokenHeader})
  }
}
