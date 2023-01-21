import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';

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
    loadChildren: () => import('../../fields/fields.module').then( m => m.FieldsPageModule)
  },
  {
    path: 'parcel/:id',
    loadChildren: () => import('../../parcel/parcel.module').then( m => m.ParcelPageModule)
  },
  {
    path: 'user-info/:username',
    loadChildren: () => import('./user-info/user-info.module').then( m => m.UserInfoPageModule)
  },
  {
    path: 'water-level',
    loadChildren: () => import('./water-level/water-level.module').then( m => m.WaterLevelPageModule)
  },
  {
    path: 'navbar',
    loadChildren: () => import('./navbar/navbar.module').then( m => m.NavbarPageModule)
  },
  {
    path: 'add-field',
    loadChildren: () => import('./add-field/add-field.module').then( m => m.AddFieldPageModule)
  },
  {
    path: 'add-parcel',
    loadChildren: () => import('./add-parcel/add-parcel.module').then( m => m.AddParcelPageModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('../../admin/admin.module').then( m => m.AdminPageModule)
  },
  {
    path: 'user-info',
    loadChildren: () => import('./user-info/user-info.module').then( m => m.UserInfoPageModule)
  },

  
  
 
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)

  ],
  exports: [RouterModule],
})
export class TabsPageRoutingModule {}
