import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-view-items',
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css']
})
export class ViewItemsComponent implements OnInit {
  items: any = [];

  constructor(private itemService: ItemService) { }

  ngOnInit(): void {
    this.items = this.itemService.itemsInService;
  }

  onRemoveItem(item: any) {
    let index = this.itemService.itemsInService.indexOf(item);
    this.itemService.itemsInService.splice(index, 1);
    this.items = this.itemService.itemsInService;
  }
  // tehke html HOME järgi

  // nupu sisse funktsioon onRemoveItem(item)

  // tee sama funktsioon ka TS-i faili onRemoveItem(item: any) {}
  // tehke kustutamine CartComponent järgi - 
  // leia index
  // kustuta Service-st
  // võta uuesti esemed Service-i seest
}
