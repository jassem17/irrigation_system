import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ForgetPage } from '../forget/forget.page';
import { FieldsPage } from '../pages/tabs/fields/fields.page';
import { TabsPage } from '../pages/tabs/tabs.page';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(
    public modalCtrl: ModalController,
  ) { }

  ngOnInit() {
  }
  async forget() {
    const modal = await this.modalCtrl.create({
      component: ForgetPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async home() {
    const modal = await this.modalCtrl.create({
      component: FieldsPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'home-modal',
    })
    return await modal.present();
  }
  async dismiss() {
    await this.modalCtrl.dismiss();
  }
}
