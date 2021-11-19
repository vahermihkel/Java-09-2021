import { Component, OnInit } from '@angular/core';
import { Item } from '../models/item.model';
import { CartService } from '../services/cart.service';
import { ItemService } from '../services/item.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  items: Item[] = [];

  constructor(private cartService: CartService,
    private itemService: ItemService) { }

  // kui componendi html avaneb
  ngOnInit(): void {
    // asünkroonne funktsioon
    // peab Observable külge subscribe - sisu läheb itemsFromBackend sisse
    // ja läheb funktsiooni tegema
    this.itemService.getItems().subscribe(itemsFromBackend => {
      this.items = itemsFromBackend;
      console.log("1");
      console.log(this.items.length)
    });
  }

  onAddToCart(item: Item) {
    this.cartService.cartItemsInService.push(item);
  }
}
