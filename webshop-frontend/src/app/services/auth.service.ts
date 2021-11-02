import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Person } from '../models/person.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedInChanged = new Subject<boolean>();

  constructor(private httpClient: HttpClient) { }

  signup(person: Person) {
    console.log(person);
    return this.httpClient.post("http://localhost:8080/add-person", person);
  }

  getUserByPersonCode(personCode: any) {
    return this.httpClient.get<Person>("http://localhost:8080/person/" + personCode);
  }
}
