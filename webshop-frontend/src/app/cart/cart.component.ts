import { Component, OnInit } from '@angular/core';
import { Item } from '../models/item.model';
import { CartService } from '../services/cart.service';
import { EverypayService } from '../services/everypay.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: Item[] = [];
  sumOfCart = 0;
  errorMessage = "";

  // constructorisse siduge cartService
  constructor(private cartService: CartService,
    private everypayService: EverypayService) { }

  // this.cartItems saab väärtuse service-i sees olevast muutujast cartItemsInService
  ngOnInit(): void {
    this.cartItems = this.cartService.cartItemsInService;
    this.calculateSumOfCart();
  }

  onEmptyCart() {
    this.cartService.cartItemsInService = [];
    this.cartItems = this.cartService.cartItemsInService;
    this.calculateSumOfCart();
  }

  onRemoveFromCart(item: Item) {
    const index = this.cartService.cartItemsInService.indexOf(item);
    this.cartService.cartItemsInService.splice(index, 1);
    this.cartItems = this.cartService.cartItemsInService;
    this.calculateSumOfCart();
  }

  private calculateSumOfCart() {
    this.sumOfCart = 0;
    this.cartItems.forEach(cartItem => {
      this.sumOfCart += cartItem.price;
    });
  }

  onPayment() {
    this.everypayService.getEverypayLink(this.cartItems).subscribe(
      response => { location.href = response.link },
      error => {
        this.errorMessage = "Unexpected error happened";
        setTimeout(() => this.errorMessage = "", 5000)
      });
  }
}
