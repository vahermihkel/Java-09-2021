import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { ItemService } from '../services/item.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  items: any[] = [];

  constructor(private cartService: CartService,
    private itemService: ItemService) { }

  ngOnInit(): void {
    console.log("jõudsin home componenti")
    // võta itemService-i seest muutuja itemsInService
    // väärtus ja pane see this.item -le
    // (CartComponent ngOnInit sees)
    this.items = this.itemService.itemsInService;
  }

  onAddToCart(item: any) {
    this.cartService.cartItemsInService.push(item);
  }
}
