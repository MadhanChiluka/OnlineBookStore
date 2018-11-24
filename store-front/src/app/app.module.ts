import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';
import { LoginService } from './services/login.service';
import { UserService } from './services/user.service';
import { MatTabsModule } from '@angular/material';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {DataTableModule} from "angular-6-datatable";


import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { MyAccountComponent } from './my-account/my-account.component';
import { HttpModule } from '@angular/http';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PaymentService } from './services/payment.service';
import { ShippingService } from './services/shipping.service';
import { BookListComponent } from './book-list/book-list.component';
import { BookService } from './services/book.service';
import { DataFilterPipe } from './book-list/data-filter.pipe';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CartService } from './services/cart.service';
import { OrderComponent } from './order/order.component';
import { OrderService } from './services/order.service';
import { CheckOutService } from './services/check-out.service';
import { OrderSummaryComponent } from './order-summary/order-summary.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    MyProfileComponent,
    BookListComponent,
    DataFilterPipe,
    BookDetailComponent,
    ShoppingCartComponent,
    OrderComponent,
    OrderSummaryComponent,
  ],
  imports: [
    BrowserModule,
    MatTabsModule,
    DataTableModule,
    MatProgressSpinnerModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      { path : '', component : HomeComponent },
      { path : 'myAccount', component : MyAccountComponent },
      { path : 'myProfile', component : MyProfileComponent},
      { path : 'bookList' , component : BookListComponent},
      { path : 'bookDetail/:id', component : BookDetailComponent},
      { path : 'shoppingCart', component : ShoppingCartComponent},
      { path : 'checkout', component : OrderComponent},
      { path : 'orderSummary', component : OrderSummaryComponent}
    ]),
    BrowserAnimationsModule
  ],
  providers: [
    LoginService,
    UserService,
    PaymentService,
    ShippingService,
    BookService,
    CartService,
    OrderService,
    CheckOutService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
