import { Component, OnInit } from '@angular/core';
import {PetitionAdd} from "./../../../model/PetitionAdd";
import {HttpService} from "./../../../services/httpServices/http.service";
import {Category} from "./../../../model/Category";
import {Router} from "@angular/router";
import {CategoryForPetition} from "../../../model/CategoryForPetition";

@Component({
    templateUrl: 'app/components/petition/add/petition.add.component.html',
    styleUrls: ['app/components/petition/add/petition.add.component.css']
})
export class PetitionAddComponent implements OnInit {

	private petition: PetitionAdd = new PetitionAdd();
	private categories: Array<CategoryForPetition> = [];
	private errorHidden: boolean = true;
	private errorMessage: string;

    constructor(private http: HttpService,
				private router : Router) { }

    ngOnInit() {
	    this.http
		    .getData("/categories")
		    .subscribe(data => {
			    var temp = data.categories;
			    for (var i = 0; i < temp.length; i++) {
				    this.categories.push(CategoryForPetition.deserialize(temp[i]));
			    }
		    });
    }

	createPetition() {
		this.errorHidden = true;
		var el = document.getElementById('error-block');
		// console.log(this.petition);
		this.http
			.sendDataNoResponse("/petition", PetitionAdd.deserialize(this.petition, this.categories))
			.catch((response) => {
				this.errorHidden = false;
				this.errorMessage = response.json().message;
				setTimeout(function(){
					el.style.display = "block";
				}, 50);
				setTimeout(function () {
					el.style.display = "none";
				}, 3000);

				return null;
			})
			.subscribe(() => {
				this.petition = null;
				this.router.navigate(['/petitions']);
			});
	}
}