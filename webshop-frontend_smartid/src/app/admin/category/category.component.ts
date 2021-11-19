import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories: string[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories = this.categoryService.categoriesInService;
  }

  onSubmit(addCategoryForm: NgForm) {
    console.log(addCategoryForm.value);
    this.categoryService.categoriesInService.push(addCategoryForm.value.category);
  }

  onRemoveCategory(category: string) {
    const index = this.categoryService.categoriesInService.indexOf(category);
    this.categoryService.categoriesInService.splice(index, 1);
    this.categories = this.categoryService.categoriesInService;
  }

}
