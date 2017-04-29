import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../../services/httpServices/http.service";
import {Country} from "../../../model/Country";

@Component({
    templateUrl: 'app/components/admin/country/admin.country.component.html',
    styleUrls: ['app/components/admin/country/admin.country.component.css']
})
export class AdminCountryComponent implements OnInit {

    private editingCountry: Country = null;
    private countries: Country[] = [];

    constructor(private http: HttpService) {

    }

    ngOnInit() {
        this.getCountries();
    }

    private getCountries() {
        this.http
            .getData("/countries")
            .subscribe(data => {

                var temp = data.countries;
                var countryArray: Country[] = [];
                for (var i = 0; i < temp.length; i++) {
                    countryArray.push(Country.deserialize(temp[i]));
                }
                this.countries = countryArray;
            });
    }

    editCountry(country: Country): void {
        if (this.editingCountry === null) {
            this.editingCountry = Country.deserialize(country);
        } else {
            alert('End editing');
        }
    }

    deleteCountry(country: any) {
        var isDelete = confirm("Delete?");
        if(isDelete) {
            if (this.editingCountry === null) {
                this.http
                    .deleteData("/country/" + country.id)
                    .subscribe(() => {
                        this.getCountries();
                    });
            } else {
                alert('End deleting!');
            }
        }
    }

    addCountry() {
        if (this.editingCountry === null) {
            this.editingCountry = new Country();
        } else {
            alert('End editing!');
        }
    }

    saveCountry(temp : boolean) {
        if (temp) {
            if (this.editingCountry.id) {
                this.http
                    .sendDataNoResponse("/country/" + this.editingCountry.id, Country.deserialize(this.editingCountry))
                    .subscribe(() => {
                        this.getCountries();
                        this.editingCountry = null;
                    });
            } else {
                this.http
                    .sendDataNoResponse("/country", Country.deserialize(this.editingCountry))
                    .subscribe(() => {
                        this.getCountries();
                        this.editingCountry = null;
                    });
            }
        } else {
            this.editingCountry = null;
        }
    }

    cancelEditing() {
        this.editingCountry = null;
    }

}
