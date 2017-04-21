import { UserComponent } from './../../../components/user/information/user.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { LoginComponent } from './../../../components/user/login/login.component';
import { RegistrationComponent } from './../../../components/user/registration/registration.component';

var userRoutes: Routes = [
		{
			path: 'user/login',
			component: LoginComponent
		},
		{
			path: 'user/registration',
			component: RegistrationComponent
		},
		{
			path: "user",
			component: UserComponent,
			children: []
		}
];

@NgModule({
	imports: [
		RouterModule.forRoot(userRoutes)
	],
	exports: [
		RouterModule
	] 
})
export class UserRoutingModule {

}