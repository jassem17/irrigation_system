import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ApiService } from 'src/app/services/api/api.service';
import { TabsPage } from '../tabs.page';
import {webSocket, WebSocketSubject} from 'rxjs/webSocket';


export interface MessageJSON {
  idSensor: string,
  idParcel: string,
  sensorType: string,
  sensorValue: number,
}

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  myWebSocket: any = webSocket('wss://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/channel');

  public info={'temp':0,'moisture':0,'water_level':'yes'};

  public systemState:any;
  slideOpts = {
    slidesPerView: 2.2,
  };
  slideOpts1 = {
    slidesPerView: 1.3,
  };
  popularItems: any[] = [];
  featuredItems: any[] = [];

  constructor(
    public modalCtrl: ModalController ,) {
      this.myWebSocket.subscribe(
        msg => {
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
  
          if(msg.sensorType=='TEMPERATURE'){
            this.info.temp=msg.sensorValue;
          }
          else if(msg.sensorType=='MOISTURE'){
            this.info.moisture=msg.sensorValue;
          }
          else if(msg.sensorType=='WATER_LEVEL'){
            this.info.water_level=msg.sensorValue;
          }
          if (this.info.water_level=='yes' ){ 
              if(this.info.temp<35 && this.info.moisture>40){
                this.systemState='YES'
              }
              else{
                this.systemState='NO'
              }
          }else{
            this.systemState='NO'
          }
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

