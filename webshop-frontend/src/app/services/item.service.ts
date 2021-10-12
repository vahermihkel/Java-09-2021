import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from '../models/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  itemsInService: Item[] = [];

  constructor(private httpClient: HttpClient) { }

  getItems() {
    // tagastab Observable objekti, mis läheb sinna URLile küsima
    // ja saab enda sisusse selle, mida selles API otsas tagastatakse
    // peab ütlema, mis on tema sisu kolmnurksete sulgude vahel
    return this.httpClient.get<Item[]>("http://localhost:8080/items");
  }

  // View.Component.ts-s teha ngOnInit sisse võtmine
  getOneItem(id: number) {
    return this.httpClient.get<Item>("http://localhost:8080/view-item/" + id);
  }
}
