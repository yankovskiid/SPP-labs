import { Component } from '@angular/core';
import { LoginService } from "./login.service.component";
import { HttpService } from "../../httpService/httpService.component";
import { LoginUser } from './LoginUser';
import { Router } from '@angular/router';

@Component ({
	templateUrl: '/resources/static/app/user/login/login_form.html',
	styleUrls: [ 'resources/static/app/user/login/login_form.css'],
	providers: [
		HttpService
	]
})
export class LoginComponent {

	public hidden = true;
	public loginUser = new LoginUser();
	public loginResponse;
	public errorMessage : string;

	constructor (private httpService : HttpService,
	             private router : Router) {
	}

	public onSubmit() {
		this.hidden = true;
		this.httpService.sendData('/login', this.loginUser)
			.catch((response) => {
				this.hidden = false;
				this.errorMessage = response.json().message;
			})
			.subscribe((response) => {
				alert("i am here");
				this.loginResponse = response;
				this.httpService.setToken(this.loginResponse.token);
				this.router.navigate(['/user', 1]);
			});
		return false;
	}
}