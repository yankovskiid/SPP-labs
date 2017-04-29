import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import {AdminMainComponent} from "../main/admin.main.component";
import {AdminRoleComponent} from "../role/admin.role.component";
import {AdminCountryComponent} from "../country/admin.country.component"
import {AdminCityComponent} from "../city/admin.city.component";

var adminRoutes: Routes = [
	{
		path: 'admin',
		component: AdminMainComponent,
		children: [
			{
				path: 'roles',
				component: AdminRoleComponent
			},
			{
				path: 'countries',
				component: AdminCountryComponent
			},
			{
				path: 'cities',
				component: AdminCityComponent
			}
		]
	}
];

@NgModule({
	imports: [
		RouterModule.forRoot(adminRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AdminRoutingModule {
}