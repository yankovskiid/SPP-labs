import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { HttpService } from "../httpService/httpService.component";

@Component ({
	template: `<h1>{{temp | json}}</h1>`,
	providers: [
		HttpService
	]
})

export class UserComponent {
	private id: string;
	private temp: any;
	constructor(private route: ActivatedRoute,
	            private httpService: HttpService) {
		this.id = this.route.snapshot.params['id'];
		this.httpService.getData('/user/' ,this.id).subscribe(data => this.temp = data);
	}
}

