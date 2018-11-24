import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';


@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http : Http) { }

  sendBook(bookId : number) {
    let url = 'http://localhost:8080/book/remove';

    let headers = new Headers( {
      'Content-Type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, bookId, {headers : headers});
  }
}
