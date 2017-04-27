import { Component, OnInit } from '@angular/core';
import { HttpService } from "./../../../services/httpServices/http.service";
import { Petition } from "./../../../model/Petition";
import { ActivatedRoute, Params } from "@angular/router";

@Component({
    templateUrl: 'app/components/petition/main/petition.main.component.html',
    styleUrls: ['app/components/petition/main/petition.main.component.css']
})
export class PetitionMainComponent implements OnInit {
    private petition: Petition = new Petition();

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute) { }

	ngOnInit() {

		this.activatedRoute.params.subscribe((params: Params) => {
			let petitionId = params['id'];
			this.http
				.getData("/petition/" + petitionId)
				.subscribe(data => {
					this.petition = Petition.deserialize(data);
				});
		});
	}
}