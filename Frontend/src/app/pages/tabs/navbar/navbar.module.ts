import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { NavbarPageRoutingModule } from './navbar-routing.module';

import { NavbarPage } from './navbar.page';
import { Routes } from '@angular/router';

const routes: Routes = [  
  {
    path: 'navbar',
    component:NavbarPage,
    children:[
      {
        path:'',
      },
      {
        path:'',
      }
  ]
  },

]

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NavbarPageRoutingModule
  ],
  declarations: [NavbarPage]
})
export class NavbarPageModule {}
