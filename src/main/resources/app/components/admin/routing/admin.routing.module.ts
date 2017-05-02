import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import {AdminMainComponent} from "../main/admin.main.component";
import {AdminRoleComponent} from "../role/admin.role.component";
import {AdminCountryComponent} from "../country/admin.country.component"
import {AdminCityComponent} from "../city/admin.city.component";
import {AdminUserComponent} from "../user/admin.user.component";
import {AdminCategoryComponent} from "../category/admin.category.component";

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
			},
			{
				path: 'users',
				component: AdminUserComponent
      },
      {
				path: 'categories',
				component: AdminCategoryComponent
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