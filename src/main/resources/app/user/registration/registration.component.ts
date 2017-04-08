import { Component } from '@angular/core';
import { RegistrationUser } from './RegistrationUser';
import { Router } from '@angular/router';
import { HttpService } from "../../httpService/http.service.component";
import 'rxjs/add/operator/catch';


@Component  ({
	templateUrl: 'app/user/registration/registration_form.html',
	styleUrls: [ 'app/user/registration/registration_form.css'],
	providers: [
		HttpService
	]
})
export class RegistrationComponent {
	public registrationResponse : any;
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
					return null;
				})
				.subscribe((response) => {
					this.registrationResponse = response;
					this.httpService.setToken(this.registrationResponse.token);
					this.router.navigate(['/user', 1]);
					return null;
				});
		}
		return false;
	}

}
