import { Component, OnInit } from '@angular/core';
import { HttpService } from "./../../../services/httpServices/http.service";
import { Petition } from "./../../../model/Petition";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {ShortPetition} from "../../../model/ShortPetition";
import {AuthenticationService} from "../../../services/httpServices/authenticationServices/authentication.service";

@Component({
    templateUrl: 'app/components/petition/main/petition.main.component.html',
    styleUrls: ['app/components/petition/main/petition.main.component.css']
})
export class PetitionMainComponent implements OnInit {
    private petition: Petition = new Petition();

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute, private router: Router, private authService: AuthenticationService) { }

	ngOnInit() {

		this.activatedRoute.params.subscribe((params: Params) => {
			let petitionId = params['id'];
			this.http
				.getData("/petition/" + petitionId)
				.subscribe(data => {
					this.petition = Petition.deserialize(data);
					this.petition.expiryDate = Math.round((new Date(this.petition.expiryDate).getTime() - new Date().getTime()) / 1000 / 3600 / 24);
				});
		});
	}

	delete(): void {
		var isDelete = confirm("Delete?");
		if(isDelete) {
			this.activatedRoute.params.subscribe((params: Params) => {
				let petitionId = params['id'];
				this.http
					.deleteData("/petition/" + petitionId)
					.subscribe(data => {
						this.router.navigate(["/petitions"]);
					});
			});
		}
	}
}