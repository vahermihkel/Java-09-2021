import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/models/item.model';
import { CartService } from 'src/app/services/cart.service';
import { ItemService } from 'src/app/services/item.service';
// absolute path on üleval ja see pannakse siis kui Visual Studio Code
// näeb, et tuleb ../../services

// kui on ../service siis jätab relative path

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {
  item!: Item;

  constructor(private route: ActivatedRoute,
    private itemService: ItemService,
    private cartService: CartService) { }

  ngOnInit(): void {
    const itemId = Number(this.route.snapshot.paramMap.get("id"));
    // const itemTitle = itemId?.replace("%60", "/");
    // let _item = this.itemService.itemsInService.find(item => item.title == itemTitle);
    // if (_item) {
    //   this.item = _item;
    // }

    // Not a Number ehk ei suutnud numbriks teha 
    if (!isNaN(itemId)) {
      this.itemService.getOneItem(itemId).subscribe(itemFromDb => {
        this.item = itemFromDb;
      })
    }
  }

  onAddToCart(item: Item) {
    this.cartService.cartItemsInService.push(item);
  }

}
