import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";
import {ShortPetition} from "../../../model/ShortPetition";

@Component({
    templateUrl: 'app/components/admin/documents/admin.documents.component.html',
    styleUrls: ['app/components/admin/documents/admin.documents.component.css']
})
export class AdminDocumentsComponent implements OnInit {

    private petitionsId: number[] = [];

    ngOnInit(): void {
        this.http
            .getData("/petitions")
            .subscribe(data => {

                var temp = data.petitions;
                for (var i = 0; i < temp.length; i++) {
                    this.petitionsId.push(ShortPetition.deserialize(temp[i]).id);
                }
            });
    }

    constructor(private http: HttpService) {

    }



}