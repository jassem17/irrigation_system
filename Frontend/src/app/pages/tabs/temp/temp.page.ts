import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { TabsPage } from '../tabs.page';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';


export interface MessageJSON {
  idSensor: string,
  idParcel: string,
  sensorType: string,
  sensorValue: number,
}

@Component({
  selector: 'app-temperature',
  templateUrl: './temp.page.html',
  styleUrls: ['./temp.page.scss'],
})
export class TempPage implements OnInit {
  
  myWebSocket: any = webSocket('ws://localhost:8080/smart_irrigation-1.0-SNAPSHOT/channel');
  public temperature :any;

 constructor(
    public modalCtrl: ModalController ,) {
      this.myWebSocket.subscribe(
        msg => {
          let temp : any
          console.log('message received: ' + msg);
          console.log("ServerResponse idSensor: " + msg.idSensor);
          console.log("ServerResponse idParcel: " + msg.idParcel);
          console.log("ServerResponse sensorType: " + msg.sensorType);
          console.log("ServerResponse sensorValue: " + msg.sensorValue);
          temp=msg.sensorValue;
          this.temperature = temp;
  
        },
        // Called whenever there is a message from the server    
        err => console.log('Erro received:', err),
        // Called if WebSocket API signals some kind of error    
        () => console.log('complete')
        // Called when connection is closed (for whatever reason)  
      );
     }

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
