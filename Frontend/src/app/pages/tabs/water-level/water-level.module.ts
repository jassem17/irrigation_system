import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { WaterLevelPageRoutingModule } from './water-level-routing.module';

import { WaterLevelPage } from './water-level.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    WaterLevelPageRoutingModule
  ],
  declarations: [WaterLevelPage]
})
export class WaterLevelPageModule {}
