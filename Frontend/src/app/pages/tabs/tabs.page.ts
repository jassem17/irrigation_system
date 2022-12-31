import { Component, OnInit, ViewChild } from '@angular/core';
import { IonTabs, ModalController } from '@ionic/angular';
import { HomePage } from './home/home.page';
import { HumidityPage } from './humidity/humidity.page';
import { WelcomePage } from 'src/app/welcome/welcome.page';
import { TempPage } from './temp/temp.page';
import { FieldsPage } from './fields/fields.page';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';
import { WaterLevelPage } from './water-level/water-level.page';


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


  selectedTab: any;
  @ViewChild('tabs', {static: false}) tabs: IonTabs;

  constructor(
    public modalCtrl: ModalController,
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
