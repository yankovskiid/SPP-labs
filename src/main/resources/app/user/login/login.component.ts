import { Component } from '@angular/core';
import { HttpService } from "../../httpService/http.service.component";
import { LoginUser } from './LoginUser';
import { Router } from '@angular/router';
import 'rxjs/add/operator/catch';


@Component ({
	templateUrl: 'app/user/login/login_form.html',
	styleUrls: [
		'app/user/registration/registration_form.css',
		'app/user/login/login_form.css'
	],
	providers: [
		HttpService
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
				alert("i am here");
				this.loginResponse = response;
				console.log(response);
				this.httpService.setToken(this.loginResponse.token);
				this.router.navigate(['/user', 1]);
				return null;
			});
		return false;
	}
}