import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TabsPage } from '../tabs.page';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

export interface MessageJSON {
  idSensor: string,
  //idParcel: string,
  sensorType: string,
  sensorValue: string,
}


@Component({
  selector: 'app-water-level',
  templateUrl: './water-level.page.html',
  styleUrls: ['./water-level.page.scss'],
})
export class WaterLevelPage implements OnInit {
  myWebSocket: any = webSocket('wss://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/channel');
  public waterLevel :any;

 constructor(
    public modalCtrl: ModalController ,) {
      this.myWebSocket.subscribe(
        msg => {
          let wl : any
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
          wl=msg.sensorValue;
          if(msg.sensorType=="WATER_LEVEL"){
            
            if(wl==0){
              this.waterLevel = "LOW";
            }
            else if(wl=1){
              this.waterLevel = "HEIGH";
            }
          }
          
  
        },
        // Called whenever there is a message from the server    
        err => console.log('Erro received:', err),
        // Called if WebSocket API signals some kind of error    
        () => console.log('complete')
        // Called when connection is closed (for whatever reason)  
      );
     }

  ngOnInit() {}
  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      this.myWebSocket.subscribe(
        msg => {
          let wl : any
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
          wl=msg.sensorValue;
          if(msg.sensorType=="WATER_LEVEL"){
            this.waterLevel = wl;
          }
          
  
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
