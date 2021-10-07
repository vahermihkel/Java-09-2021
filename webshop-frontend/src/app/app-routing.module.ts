import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddItemComponent } from './admin/add-item/add-item.component';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { CategoryComponent } from './admin/category/category.component';
import { EditItemComponent } from './admin/edit-item/edit-item.component';
import { ViewItemsComponent } from './admin/view-items/view-items.component';
import { CartComponent } from './cart/cart.component';
import { HomeComponent } from './home/home.component';
import { ViewComponent } from './home/view/view.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';

const routes: Routes = [
  // localhost:4200
  { path: "", component: HomeComponent },
    // localhost:4200/ostukorv
  { path: "ostukorv", component: CartComponent },
  { path: "ese/:id", component: ViewComponent },
  { path: "admin", component: AdminHomeComponent },
  { path: "admin/lisa-ese", component: AddItemComponent },
  { path: "admin/muuda-ese/:itemId", component: EditItemComponent },
  { path: "admin/esemed", component: ViewItemsComponent },
  { path: "admin/kategooriad", component: CategoryComponent },
  { path: "**", component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
