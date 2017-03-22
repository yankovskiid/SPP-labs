import { Component } from '@angular/core';
import { UserService } from "./user.service.component.ts";
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component ({
	// selector: 'my-app',
	template: `<h1>{{temp | json}}</h1>`,
	providers: [
		UserService
	]
})

export class UserComponent {
	private id: string;
	private temp: any;
	constructor(private route: ActivatedRoute,
	            private userService: UserService) {
		this.id = this.route.params['id'];
		userService.getData(this.id).subscribe(data => this.any = data);
	}
}

