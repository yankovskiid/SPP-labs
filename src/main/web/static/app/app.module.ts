import { RouterModule, Routes } from '@angular/router';
import { BrowserModule }  from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { APP_BASE_HREF } from '@angular/common';
import { MyApp } from './app.component.ts';
import { UserComponent } from "./user/user.component.ts";
import { AppRoutingModule } from "./app.routing.ts";
import { MainComponent } from "./main/main.component";


@NgModule({
    imports: [
    	BrowserModule,
	    HttpModule,
	    AppRoutingModule
    ],
    declarations: [
    	MyApp,
	    UserComponent,
	    MainComponent
    ],
    bootstrap: [MyApp]
})

export class MyAppModule { }
