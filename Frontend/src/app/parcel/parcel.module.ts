import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ParcelPageRoutingModule } from './parcel-routing.module';

import { ParcelPage } from './parcel.page';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ParcelPageRoutingModule,
    HttpClientModule
  ],
  declarations: [ParcelPage]
})
export class ParcelPageModule {}
