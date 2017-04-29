import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import {AdminMainComponent} from "../main/admin.main.component";
import {AdminRoleComponent} from "../role/admin.role.component";

var adminRoutes: Routes = [
	{
		path: 'admin',
		component: AdminMainComponent,
		children: [
			{
				path: 'roles',
				component: AdminRoleComponent
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