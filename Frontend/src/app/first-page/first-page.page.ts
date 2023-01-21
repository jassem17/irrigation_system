import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { BehaviorSubject } from 'rxjs';
import { LoginPage } from '../login/login.page';
import { WelcomePage } from '../welcome/welcome.page';

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.page.html',
  styleUrls: ['./first-page.page.scss'],
})
export class FirstPagePage implements OnInit {


  choice = "USER";
  public logic:any 
  constructor(public modalCtrl: ModalController,private router:Router) { }

  ngOnInit() {
  }

  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      event.target.complete();
    }, 2000);
  };

  async Admin() {
    const mySubject = new BehaviorSubject("ADMIN");

    const modal = await this.modalCtrl.create({
      component: LoginPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'login-modal',
      componentProps: {
        mySubject
      },
    });
    mySubject.subscribe(() => {
      this.choice = "ADMIN";
    });

    modal.onDidDismiss().then((_ => {
      mySubject.unsubscribe();
    }));

    this.router.navigate(['login'])
    this.logic="ADMIN"
    return await modal.present();
  }

  async User() {
    const mySubject = new BehaviorSubject("USER");

    const modal = await this.modalCtrl.create({
      component: WelcomePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'first-page-modal',
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
    
   

    return await modal.present();
  }

}
