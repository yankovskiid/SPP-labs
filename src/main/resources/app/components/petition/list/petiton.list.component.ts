import { HttpService } from './../../../services/httpServices/http.service';
import { ShortPetition } from './../../../model/ShortPetition';
import { Component, OnInit } from '@angular/core';

@Component({
    templateUrl: 'app/components/petition/list/petition.list.component.html',
    styleUrls: [ 'app/components/petition/list/petition.list.component.css' ]
})
export class PetitionListComponent implements OnInit {
    
    private petitions: ShortPetition[] = [];

    constructor(private http: HttpService) { }

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