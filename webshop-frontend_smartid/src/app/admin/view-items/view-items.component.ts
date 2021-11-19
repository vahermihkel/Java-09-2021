import { Component, OnInit } from '@angular/core';
import { Item } from 'src/app/models/item.model';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-view-items',
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit {
  items: Item[] = [];
  message = "";

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    this.itemService.getItems().subscribe(itemsFromBackend => {
      this.items = itemsFromBackend;
    });
  }

  onRemoveItem(item: Item) {
    this.itemService.deleteItem(Number(item.id)).subscribe(
      () => {
        console.log("EDUKAS");
        this.itemService.getItems().subscribe(itemsFromBackend => {
          this.items = itemsFromBackend;
        });
      },
      errorResponse => {
        console.log("SAI ERRORI")
        console.log(errorResponse.error.message);
        this.message = errorResponse.error.message;
        setTimeout(() => this.message = "", 3000);
      });
  }
  // tehke html HOME järgi

  // nupu sisse funktsioon onRemoveItem(item)

  // tee sama funktsioon ka TS-i faili onRemoveItem(item: Item) {}
  // tehke kustutamine CartComponent järgi - 
  // leia index
  // kustuta Service-st
  // võta uuesti esemed Service-i seest
}
