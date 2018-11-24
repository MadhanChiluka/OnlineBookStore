import { ShoppingCart } from "./shopping-cart";
import { Book } from "./book";

export class CartItem {
    public id : number;
    public qty : number;
    public subtotal : number;
    public book : Book;
    public shoppingCart : ShoppingCart;
    public toUpdate : boolean;
}
