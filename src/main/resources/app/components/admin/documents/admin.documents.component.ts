import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";

@Component({
    templateUrl: 'app/components/admin/documents/admin.documents.component.html',
    styleUrls: ['app/components/admin/documents/admin.documents.component.css']
})
export class AdminDocumentsComponent implements OnInit {

    ngOnInit(): void {
    }

    constructor(private http: HttpService) {

    }



}