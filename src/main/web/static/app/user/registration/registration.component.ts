import { Component } from '@angular/core';
import { RegistrationUser } from './RegistrationUser';
import { Router } from '@angular/router';
import { HttpService } from "../../httpService/httpService.component";

@Component  ({
	templateUrl: '/resources/static/app/user/registration/registration_form.html',
	styleUrls: [ 'resources/static/app/user/registration/registration_form.css'],
	providers: [
		HttpService
	]
})
export class RegistrationComponent {
	public registrationResponse;
	public errorMessage : string;
	public hidden = true;
	public registrationUser = new RegistrationUser();
	constructor (private httpService : HttpService,
	             private router : Router,) {
	}

	public onSubmit() {
		this.hidden = true;
		if (this.registrationUser.password !== this.registrationUser.repeatPassword) {
			this.errorMessage = "Repeat password and password, must be equals!";
			this.hidden = false;
		} else {
			this.httpService.sendData('/registration', this.registrationUser)
				.catch((response) => {
					this.hidden = false;
					this.errorMessage = response.json().message;
				})
				.subscribe((response) => {
					this.registrationResponse = response;
					this.httpService.setToken(this.registrationReponse.token);
					this.router.navigate(['/user', 1]);
				});
		}
		return false;
	}

}
