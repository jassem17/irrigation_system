import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ApiService } from 'src/app/services/api/api.service';
import { TabsPage } from '../tabs.page';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  slideOpts = {
    slidesPerView: 2.2,
  };
  slideOpts1 = {
    slidesPerView: 1.3,
  };
  popularItems: any[] = [];
  featuredItems: any[] = [];

  constructor(
    public modalCtrl: ModalController ,) { }

  ngOnInit() {}
    async tab(){
      const modal = await this.modalCtrl.create({
        component: TabsPage,
        animated: true,
        mode: 'ios',
        backdropDismiss: false,
        cssClass: 'home-modal',
      })
      return await modal.present();
    }
  }

