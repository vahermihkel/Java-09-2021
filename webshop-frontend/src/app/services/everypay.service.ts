import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EverypayService {
  constructor(private httpClient: HttpClient) { }

  getEverypayLink(amount: number) {
    return this.httpClient.post<any>("http://localhost:8080/payment", { totalSum: amount });
  }
}
