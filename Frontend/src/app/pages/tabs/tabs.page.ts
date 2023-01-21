import { Component, OnInit, ViewChild } from '@angular/core';
import { IonTabs, ModalController, NavController } from '@ionic/angular';
import { HomePage } from './home/home.page';
import { HumidityPage } from './humidity/humidity.page';
import { WelcomePage } from 'src/app/welcome/welcome.page';
import { TempPage } from './temp/temp.page';
import { FieldsPage } from '../../fields/fields.page';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';
import { WaterLevelPage } from './water-level/water-level.page';
import { Router } from '@angular/router';
import { FirstPagePage } from 'src/app/first-page/first-page.page';



export interface MessageJSON {
  idSensor: string,
  idParcel: string,
  sensorType: string,
  sensorValue: number,
}

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.page.html',
  styleUrls: ['./tabs.page.scss'],
})
export class TabsPage implements OnInit {

  myWebSocket: any = webSocket('wss://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/channel');


  public user:any
  selectedTab: any;
  @ViewChild('tabs', {static: false}) tabs: IonTabs;

  constructor(
    public modalCtrl: ModalController,private router:Router,public navCtrl:NavController
    ) {
      this.myWebSocket.subscribe(
        msg => {
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
  
        },
        // Called whenever there is a message from the server    
        err => console.log('Erro received:', err),
        // Called if WebSocket API signals some kind of error    
        () => console.log('complete')
        // Called when connection is closed (for whatever reason)  
      );
      
     }


  ngOnInit() {
    this.user=localStorage.getItem('currentUser');
    this.user=JSON.parse(localStorage.getItem('currentUser'))["username"];
  
    
  }

  async goToFieldsPage(){
    this.navCtrl.navigateForward('fields');
    const modal = await this.modalCtrl.create({
      component: FieldsPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }

  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      this.myWebSocket.subscribe(
        msg => {
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
  
        },
        // Called whenever there is a message from the server    
        err => console.log('Erro received:', err),
        // Called if WebSocket API signals some kind of error    
        () => console.log('complete')
        // Called when connection is closed (for whatever reason)  
      );
      event.target.complete();
    }, 2000);
  };
  isLoggedIn() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }
    return false;
  }
  async logout() {
    localStorage.removeItem('currentUser');
    this.router.navigate(['first-page']);
    const modal = await this.modalCtrl.create({
      component: FirstPagePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();

  }
  async state () {
    const modal = await this.modalCtrl.create({
      component: HomePage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async temperature () {
    const modal = await this.modalCtrl.create({
      component: TempPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    return await modal.present();
  }
  async humidity () {
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
    const modal2 = await this.modalCtrl.create({
      component: FieldsPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'forget-modal',
    })
    if (this.isLoggedIn){
      return await modal2.present();
    }
    return await modal.present();
  }
  async waterLevel () {
    const modal = await this.modalCtrl.create({
      component: WaterLevelPage,
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
