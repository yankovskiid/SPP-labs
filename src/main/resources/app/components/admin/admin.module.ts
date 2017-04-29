import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import {AdminRoleComponent} from "./role/admin.role.component";
import {RoleEditComponent} from "./role/edit/role.edit.component";
import {AdminRoutingModule} from "./routing/admin.routing.module";
import {AdminMainComponent} from "./main/admin.main.component";
import {CountryEditComponent} from "./country/edit/country.edit.component";
import {AdminCountryComponent} from "./country/admin.country.component";
import {AdminCityComponent} from "./city/admin.city.component";
import {CityEditComponent} from "./city/edit/city.edit.component";
import {AdminUserComponent} from "./user/admin.user.component";
import {UserEditComponent} from "./user/edit/user.edit.component";

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
		RoleEditComponent,
		AdminCountryComponent,
		CountryEditComponent,
		AdminCityComponent,
		CityEditComponent,
		AdminUserComponent,
		UserEditComponent
	],
	exports: [
		AdminMainComponent
	]
})

export class AdminModule {
	constructor() {
	}
}