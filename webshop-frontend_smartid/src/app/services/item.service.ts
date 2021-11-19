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
    return this.httpClient.get<Item[]>("http://localhost:8080/api/items");
  }

  // View.Component.ts-s teha ngOnInit sisse võtmine
  getOneItem(id: number) {
    return this.httpClient.get<Item>("http://localhost:8080/view-item/" + id);
  }

  deleteItem(id: number) {
    return this.httpClient.delete("http://localhost:8080/delete-item/" + id);
  }

  addItem(item: Item) {
    return this.httpClient.post("http://localhost:8080/add-item", item);
  }

  editItem(item: Item) {
    return this.httpClient.post("http://localhost:8080/edit-item", item);
  }
}
