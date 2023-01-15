import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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


  public login : FormGroup
  private urlLogin="https://smart-irrigation.me:8443/smart_irrigation-1.0-SNAPSHOT/api/oauth2/token";

  constructor(
    public modalCtrl: ModalController,private http:HttpClient,private fb:FormBuilder,private router:Router) {
    this.login = this.fb.group(
      {
      username : ['',Validators.required],
      password: ['',Validators.required],
      grandType:"PASSWORD"
    }

    );
   }

  ngOnInit() {
  }

  SignIn(){
    let body=this.login.value;
    console.log(body)
    
    this.http.post(this.urlLogin,body).subscribe(
      data=>{
         console.log(data)
         
         localStorage.setItem('currentUser', JSON.stringify(body));
         console.log(localStorage)
         this.router.navigate(['fields']);
        }
      ,err=>{
        console.log(err)
        alert('Invalid username or password')
      }
    )
  }
  isLoggedIn() {
    if (localStorage.getItem('currentUser')) {
      return true;
    }
    return false;
  }
  logout() {
    localStorage.removeItem('currentUser');
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
