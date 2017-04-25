import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component ({
	templateUrl: 'app/components/main/main.component.html'
})

export class MainComponent {
	private id: number;
	constructor() {
		this.id = 1;
	}
}