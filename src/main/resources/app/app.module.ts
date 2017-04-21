import { RouterModule } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { RegistrationComponent } from './components/user/registration/registration.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MyApp } from './components/app/app.component';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './modules/main/routing/app.routing.module';
import { HttpService } from './services/httpServices/http.service';
import { MainComponent } from './components/main/main.component';
import { UserModule } from './modules/user/user.module';

@NgModule({
    imports: [
    	BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule,

        UserModule,
        AppRoutingModule
        
    ],
    declarations: [
    	MyApp,
        MainComponent
    ],
    bootstrap: [ MyApp ],
    providers: [ HttpService ]
})

export class MyAppModule { 
    constructor() {
    }
}