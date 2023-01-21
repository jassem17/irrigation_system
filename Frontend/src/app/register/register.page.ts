import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { LoginPage } from '../login/login.page';
@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

private urlRegister= "https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/person/signup"

  public register : FormGroup
  
  constructor(
    public modalCtrl: ModalController, private fb:FormBuilder, private http:HttpClient,private router:Router) {
    this.register = this.fb.group({
      username :['',Validators.required],
      email : ['', Validators.required],
      password: ['',Validators.required],
      roles:[["USER"]],
      userId:555
    }
    ) ;
   }

  ngOnInit() {
  }
  handleRefresh(event) {
    setTimeout(() => {
      // Any calls to load data go here
      event.target.complete();
    }, 2000);
  };

signUp(){
  let body = this.register.value;
  console.log(body)
  this.http.post(this.urlRegister,body).subscribe(
    async data => {
      console.log(data)
      this.router.navigate(['login'])
      const modal = await this.modalCtrl.create({
        component: LoginPage,
        animated: true,
        mode: 'ios',
        backdropDismiss: false,
        cssClass: 'admin-modal',
      })
      return await modal.present();
    },err=>{
      console.log(err)
      alert('invalid information')
    }
  )
}

  async log() {
    const modal = await this.modalCtrl.create({
      component: LoginPage,
      animated: true,
      mode: 'ios',
      backdropDismiss: false,
      cssClass: 'login-modal',
    })

    return await modal.present();
  }

  async dismiss() {
    return await this.modalCtrl.dismiss();
  }

}
