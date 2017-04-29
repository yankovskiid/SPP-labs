import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";

@Component({
	templateUrl: 'app/components/admin/main/admin.main.component.html',
	styleUrls: ['app/components/admin/main/admin.main.component.css']
})
export class AdminMainComponent implements OnInit {

	ngOnInit(): void {
	}

	constructor(private http: HttpService) {

	}

}