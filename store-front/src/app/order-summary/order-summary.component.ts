import { Component, OnInit } from '@angular/core';
import { AppConst } from '../constants/app-constant';
import { Order } from '../models/order';
import { CartItem } from '../models/cart-item';
import { CheckOutService } from '../services/check-out.service';
import { Router, Params, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {
private servePath = AppConst.servePath;
private order : Order = new Order();
private estimatedDeliveryDate : string;
private cartItemList;
  constructor(
    private checkoutService : CheckOutService,
    private router : Router,
    private route : ActivatedRoute
  ) { }
  

  ngOnInit() {
    this.route.queryParams.subscribe( params => {
      this.order = JSON.parse(params['orders']);

      let deliveryDate = new Date();

      if(this.order.shippingMethod=="groundShipping"){
        deliveryDate.setDate(deliveryDate.getDate()+5);
      }else {
        deliveryDate.setDate(deliveryDate.getDate()+3)
      }

      let days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      this.estimatedDeliveryDate = days[deliveryDate.getDay()]+", "+deliveryDate.getDate()+'/ '+deliveryDate.getMonth()+"/"+deliveryDate.getFullYear();
    
      this.cartItemList = this.order.cartItemList;
    })
  }

}
