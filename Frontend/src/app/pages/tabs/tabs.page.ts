import { Component, OnInit, ViewChild } from '@angular/core';
import { IonTabs, ModalController } from '@ionic/angular';
import { HomePage } from './home/home.page';
import { HumidityPage } from './humidity/humidity.page';
import { WelcomePage } from 'src/app/welcome/welcome.page';
import { TempPage } from './temp/temp.page';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.page.html',
  styleUrls: ['./tabs.page.scss'],
})
export class TabsPage implements OnInit {

  selectedTab: any;
  @ViewChild('tabs', {static: false}) tabs: IonTabs;

  constructor(
    public modalCtrl: ModalController,
    ) { }

  ngOnInit() {
  }
  async home () {
    const modal = await this.modalCtrl.create({
      component: HomePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async sunny () {
    const modal = await this.modalCtrl.create({
      component: TempPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async water () {
    const modal = await this.modalCtrl.create({
      component: HumidityPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async welc () {
    const modal = await this.modalCtrl.create({
      component: WelcomePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }


  

  setCurrentTab() {
    this.selectedTab = this.tabs.getSelected();
    console.log(this.selectedTab);
  }

}
