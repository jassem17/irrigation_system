import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddFieldPageRoutingModule } from './add-field-routing.module';

import { AddFieldPage } from './add-field.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddFieldPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AddFieldPage]
})
export class AddFieldPageModule {}
