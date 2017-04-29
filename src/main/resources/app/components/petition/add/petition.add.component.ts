import { Component, OnInit } from '@angular/core';
import {PetitionAdd} from "./../../../model/PetitionAdd";
import {HttpService} from "./../../../services/httpServices/http.service";
import {Category} from "./../../../model/Category";

@Component({
    templateUrl: 'app/components/petition/add/petition.add.component.html',
    styleUrls: ['app/components/petition/add/petition.add.component.css']
})
export class PetitionAddComponent implements OnInit {

	private petition: PetitionAdd = new PetitionAdd();
	private categories: Array<Category> = [];

    constructor(private http: HttpService) { }

    ngOnInit() {
	    this.http
		    .getData("/categories")
		    .subscribe(data => {
			    var temp = data.categories;
			    for (var i = 0; i < temp.length; i++) {
				    this.categories.push(Category.deserialize(temp[i]));
			    }
		    });
    }

	createPetition() {
		alert('Send data');
	}
}