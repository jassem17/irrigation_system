import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FieldsPageRoutingModule } from './fields-routing.module';

import { FieldsPage } from './fields.page';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FieldsPageRoutingModule,
    HttpClientModule
  ],
  declarations: [FieldsPage]
})
export class FieldsPageModule {}
