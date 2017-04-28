import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import {AdminRoleComponent} from "./role/admin.role.component";
import {RoleEditComponent} from "./role/edit/role.edit.component";
import {AdminRoutingModule} from "./routing/admin.routing.module";
import {AdminMainComponent} from "./main/admin.main.component";

@NgModule({
	imports: [
		BrowserModule,
		HttpModule,
		FormsModule,
		RouterModule,

		AdminRoutingModule
	],
	declarations: [
		AdminMainComponent,
		AdminRoleComponent,
		RoleEditComponent
	],
	exports: [
		AdminMainComponent
	]
})

export class AdminModule {
	constructor() {
	}
}