import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { BehaviorSubject } from 'rxjs';
import { FirstPagePage } from '../first-page/first-page.page';
import { LoginPage } from '../login/login.page';
import { RegisterPage } from '../register/register.page';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.page.html',
  styleUrls: ['./welcome.page.scss'],
})
export class WelcomePage implements OnInit {

  choice = "USER";

  constructor(
    public modalCtrl: ModalController,private router:Router
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
    const mySubject = new BehaviorSubject("USER");
    const modal = await this.modalCtrl.create({
      component: LoginPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'login-modal',
      componentProps: {
        mySubject
      },
    })
    mySubject.subscribe(() => {
      this.choice = "USER";
    });

    modal.onDidDismiss().then((_ => {
      mySubject.unsubscribe();
    }));
    

    this.router.navigate(['login'])
    return await modal.present();
  }

  async register() {
    const modal = await this.modalCtrl.create({
      component: RegisterPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'register-modal',
    })

    this.router.navigate(['register'])
    return await modal.present();
  }

  async tab(){
    const modal = await this.modalCtrl.create({
      component: FirstPagePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'home-modal',
    })
    return await modal.present();
  }
}
