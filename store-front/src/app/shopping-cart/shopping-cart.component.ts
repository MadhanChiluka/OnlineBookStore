import { Component, OnInit } from '@angular/core';
import { AppConst } from '../constants/app-constant';
import { Book } from '../models/book';
import { CartItem } from '../models/cart-item';
import { ShoppingCart } from '../models/shopping-cart';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  private servePath = AppConst.servePath;
  private selectedBook : Book;
  private cartItemList : CartItem[] = [];
  private cartItemNumber : number;
  private shoppingCart : ShoppingCart = new ShoppingCart();
  private emptyCart : boolean;
  private notEnoughStock : boolean;
  private cartItemUpdated : boolean;

  constructor(private router : Router, private cartService : CartService) { }


  onSelect(book : Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id])
  }

  onRemoveCartItem(cartItem : CartItem) {
    this.cartService.removeCartItem(cartItem.id).subscribe(
      res => {
        console.log(res.json());
        this.getCartItemList();
        this.getShoppingCart();
      },
      error => {
        console.log(error);
      }
    )
  }

  onUpdateCartItem(cartItem : CartItem) {
    this.cartService.updateCartItem(cartItem.id, cartItem.qty).subscribe(
      res => {
        console.log(res.json());
        this.cartItemUpdated = true;
        this.getShoppingCart();
      },
      error => {
        console.log(error);
      }
    )
  }

  getCartItemList() {
    this.cartService.getCartItemList().subscribe(
      res => {
        this.cartItemList = res.json();
        this.cartItemNumber = this.cartItemList.length;   
      },
      error => {
        console.log(error);
      }
    )
  }

  getShoppingCart() {
    this.cartService.getShoppingCart().subscribe(
      res => {
        this.shoppingCart = res.json();
      },
      error => {
        console.log(error);
      }
    )
  }

  onCheckout() {
    if(this.cartItemNumber==0){
      this.emptyCart = true;
    } else {
      for(let item of this.cartItemList) {
        if(item.qty > item.book.inStockNumber){
          console.log("Not Enough Stock");
          this.notEnoughStock =true;
          return;
        }
      }
     // this.router.navigate(['/order'])
    }

  }

  ngOnInit() {
    this.getCartItemList();
    this.getShoppingCart();
  }

}
