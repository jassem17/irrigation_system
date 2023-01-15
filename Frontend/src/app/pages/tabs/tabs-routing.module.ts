import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children: [
      {
        path: 'home',
        loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
      },
      {
        path: 'humidity',
        loadChildren: () => import('./humidity/humidity.module').then( m => m.HumidityPageModule)
      },
      {
        path: 'temperature',
        loadChildren: () => import('./temp/temp.module').then( m => m.TempPageModule)
      },
      {
        path: '',
        redirectTo: '/tabs/home',
        pathMatch: 'full'
      },
      
    ]
  }, 
  {
    path: 'fields',
    loadChildren: () => import('./fields/fields.module').then( m => m.FieldsPageModule)
  },
  {
    path: 'parcel/:id',
    loadChildren: () => import('./parcel/parcel.module').then( m => m.ParcelPageModule)
  },  {
    path: 'water-level',
    loadChildren: () => import('./water-level/water-level.module').then( m => m.WaterLevelPageModule)
  },
  {
    path: 'navbar',
    loadChildren: () => import('./navbar/navbar.module').then( m => m.NavbarPageModule)
  },

  
  
 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsPageRoutingModule {}
