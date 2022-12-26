import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { WaterLevelPage } from './water-level.page';

const routes: Routes = [
  {
    path: '',
    component: WaterLevelPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WaterLevelPageRoutingModule {}
