import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EverypayLink } from '../models/everypay-link.interface';
import { Item } from '../models/item.model';

@Injectable({
  providedIn: 'root'
})
export class EverypayService {
  constructor(private httpClient: HttpClient) { }

  getEverypayLink(orderItems: Item[]) {
    let personCode = sessionStorage.getItem("user");
    return this.httpClient.post<EverypayLink>("http://localhost:8080/payment", 
              {personCode: personCode, items: orderItems});
  }
}
