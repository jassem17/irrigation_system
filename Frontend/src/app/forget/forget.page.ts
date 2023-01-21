import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { LoginPage } from '../login/login.page';
import { ResetPage } from '../reset/reset.page';
@Component({
  selector: 'app-forget',
  templateUrl: './forget.page.html',
  styleUrls: ['./forget.page.scss'],
})
export class ForgetPage implements OnInit {

  constructor(
    public modalCtrl: ModalController,
  ) { }

  ngOnInit() {
  }
  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      event.target.complete();
    }, 2000);
  };
  async login() {
    const modal = await this.modalCtrl.create({
      component: LoginPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'login-modal',
    })
    return await modal.present();
  }
    async reset() {
      const modal = await this.modalCtrl.create({
        component: ResetPage,
        animated: true,
        mode: 'ios',
        backdropDismiss: false,
        cssClass: 'reset-modal',
      })

    return await modal.present();
  }

  async dismiss() {
    return await this.modalCtrl.dismiss();
  }

}
