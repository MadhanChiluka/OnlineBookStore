import { Component, OnInit } from '@angular/core';
import { Book } from '../models/book';
import { AppConst } from '../constants/app-constant';
import { BookService } from '../services/book.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  private bookId : number;
  private book : Book = new Book();
  private servePath = AppConst.servePath;
  private numberList : number[] = [1,2,3,4,5,6,7,8,9];
  private qty : number;

  private addBookSuccess : boolean = false;
  private notEnoughStock : boolean = false;

  constructor(
    private bookService : BookService,
    private cartService : CartService,
    private router : Router,
    private route : ActivatedRoute
  ) { }

  onAddToCart() {
    this.cartService.addItem(this.bookId, this.qty).subscribe(
      res => {
        console.log(res);
        this.addBookSuccess = true;
      },
      error => {
        console.log(error);
        this.notEnoughStock = true;
      }
    )
  }

  ngOnInit() {
    this.route.params.forEach((params : Params) =>{
      this.bookId = Number.parseInt(params['id']);
    });

    this.bookService.getBook(this.bookId).subscribe(
      res => {
        this.book = res.json();
      },
      error => {
        console.log(error);
      }
    )
    this.qty = 1;
  }

}
