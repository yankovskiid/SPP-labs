import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";

@Component({
	templateUrl: 'app/components/admin/role/admin.role.component.html',
	styleUrls: ['app/components/admin/role/admin.role.component.css']
})
export class AdminRoleComponent implements OnInit {

	ngOnInit(): void {
	}

	constructor(private http: HttpService) {

	}

}