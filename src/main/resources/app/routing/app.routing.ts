import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

import { UserComponent } from '../user/user.component';
import { MainComponent } from '../main/main.component';
import { RegistrationComponent } from '../user/registration/registration.component';
import { LoginComponent } from '../user/login/login.component';

const appRoutes: Routes = [
	{
		path: "user/:id",
		component: UserComponent
	},
	{
		path: "",
		component: MainComponent
	},
	{
		path: "registration",
		component: RegistrationComponent
	},
	{
		path: "login",
		component: LoginComponent
    }
];
@NgModule({
	imports: [
		RouterModule.forRoot(appRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AppRoutingModule {}