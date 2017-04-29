import { Component } from '@angular/core';
import { RegistrationUser } from './../../../model/RegistrationUser';
import { Router } from '@angular/router';
import { HttpService } from "./../../../services/httpServices/http.service";
import 'rxjs/add/operator/catch';


@Component  ({
	templateUrl: 'app/components/user/registration-component/registration.component.html',
	styleUrls: [ 'app/components/user/registration-component/registration.component.css'],
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
		var el = document.getElementById('error-block');
		if (this.registrationUser.password !== this.registrationUser.repeatPassword) {
			this.errorMessage = "Repeat password and password, must be equals!";
			setTimeout(function(){
				el.style.display = "block";
			}, 50);
			setTimeout(function () {
				el.style.display = "none";
			}, 3000);
			this.hidden = false;
		} else {
			this.httpService.sendData('/registration', this.registrationUser)
				.catch((response) => {
					this.hidden = false;
					this.errorMessage = response.json().message;
					setTimeout(function(){
						el.style.display = "block";
					}, 50);
					setTimeout(function () {
						el.style.display = "none";
					}, 3000);

					return null;
				})
				.subscribe((response) => {
					this.registrationResponse = response;
					this.httpService.setToken(this.registrationResponse.token);

					this.router.navigate(['/']);
					return null;
				});
		}
		return false;
	}

}
