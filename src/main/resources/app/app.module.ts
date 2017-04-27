import { PetitionModule } from './components/petition/petition.module';
import { UserModule } from './components/user/user.module';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MyApp } from './components/app/app.component';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app.routing.module';
import { HttpService } from './services/httpServices/http.service';
import { MainComponent } from './components/main/main.component';

@NgModule({
    imports: [
    	BrowserModule,
        HttpModule,
        FormsModule,
        RouterModule,

        UserModule,
        PetitionModule,
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