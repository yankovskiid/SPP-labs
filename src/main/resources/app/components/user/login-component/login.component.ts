import { Component } from '@angular/core';
import { HttpService } from "./../../../services/httpServices/http.service";
import { LoginUser } from './../../../model/LoginUser';
import { Router } from '@angular/router';
import 'rxjs/add/operator/catch';


@Component ({
	templateUrl: 'app/components/user/login-component/login.component.html',
	styleUrls: [
		'app/components/user/registration-component/registration.component.css',
		'app/components/user/login-component/login.component.css'
	]
})
export class LoginComponent {

	public hidden = true;
	public loginUser = new LoginUser();
	public loginResponse : any;
	public errorMessage : string;

	constructor (private httpService : HttpService,
	             private router : Router) {
	}

	public onSubmit() {
		this.hidden = true;
		var el = document.getElementById('error-block');
		this.httpService.sendData('/login', this.loginUser)
			.catch((response) => {
				this.hidden = false;
				this.errorMessage = response.json().message;
				setTimeout(function(){
					el.style.display = "block";
				}, 50);
				setTimeout(function () {
					el.style.display = "none";
				}, 3000);
				// setTimeout(function () { }, 5000);
				// setTimeout(function(){
				// 	el.style.display = "none";
				// }, 100);
				return null;
			})
			.subscribe((response) => {
				this.loginResponse = response;
				this.httpService.setToken(this.loginResponse.token);
				this.router.navigate(['/']);
				return null;
			});
		return false;
	}
}