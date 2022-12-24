import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TabsPage } from '../tabs.page';
@Component({
  selector: 'app-humidity',
  templateUrl: './humidity.page.html',
  styleUrls: ['./humidity.page.scss'],
})
export class HumidityPage implements OnInit {

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
