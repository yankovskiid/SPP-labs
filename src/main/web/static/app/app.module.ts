import { RouterModule, Routes } from '@angular/router';
import { BrowserModule }  from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { APP_BASE_HREF } from '@angular/common';
import { MyApp } from './app.component.ts';
import { UserComponent } from "./user/user.component.ts";
import { AppRoutingModule } from "./app.routing.ts";
import { MainComponent } from "./main/main.component";
import { RegistrationComponent } from "./user/registration/registration.component";
import { LoginComponent } from "./user/login/login.component";
import { FormsModule }   from '@angular/forms';

@NgModule({
    imports: [
    	BrowserModule,
	    HttpModule,
	    AppRoutingModule,
	    FormsModule
    ],
    declarations: [
    	MyApp,
	    UserComponent,
	    MainComponent,
	    RegistrationComponent,
	    LoginComponent
    ],
    bootstrap: [MyApp]
})

export class MyAppModule { }
