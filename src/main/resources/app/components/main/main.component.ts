import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {AuthenticationService} from "../../services/httpServices/authenticationServices/authentication.service";
import {HttpService} from "../../services/httpServices/http.service";

@Component ({
	templateUrl: 'app/components/main/main.component.html'
})

export class MainComponent {
	private id: number;
	constructor(private authService: AuthenticationService,
				private http: HttpService) {
		this.id = 1;
	}
}