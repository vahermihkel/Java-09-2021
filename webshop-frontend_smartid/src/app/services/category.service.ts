import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  categoriesInService = ["watch", "telephone", "laptop"];

  constructor() { }
}
