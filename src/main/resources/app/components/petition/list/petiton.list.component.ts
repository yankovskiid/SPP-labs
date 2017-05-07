import { HttpService } from './../../../services/httpServices/http.service';
import { ShortPetition } from './../../../model/ShortPetition';
import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../../services/httpServices/authenticationServices/authentication.service";

@Component({
    templateUrl: 'app/components/petition/list/petition.list.component.html',
    styleUrls: [ 'app/components/petition/list/petition.list.component.css' ]
})
export class PetitionListComponent implements OnInit {

    private petitions: ShortPetition[] = [];

    constructor(private http: HttpService,
                private authService: AuthenticationService ) { }

    ngOnInit() { 
        this.http
            .getData("/petitions")
            .subscribe(data => {

				var temp = data.petitions;
	            for (var i = 0; i < temp.length; i++) {
		            this.petitions.push(ShortPetition.deserialize(temp[i]));
	            }
            });
    }
}