import {Component, OnInit} from "@angular/core";
import {HttpService} from "../../../services/httpServices/http.service";
import {City} from "../../../model/City";

@Component({
    templateUrl: "app/components/admin/city/admin.city.component.html",
    styleUrls: ['app/components/admin/city/admin.city.component.css']
})
export class AdminCityComponent implements OnInit {

    private editingCity: City = null;
    private cities: City[] = [];
    private errorHidden: boolean = true;
    private errorMessage: string;

    constructor(private http: HttpService) {}

    ngOnInit() {
        this.getCities();
    }

    addCity() {
        if(this.editingCity == null) {
            this.editingCity = new City();
        } else {
            alert("End editing!");
        }
    }

    editCity(city: City) {
        if(this.editingCity == null) {
            this.editingCity = City.deserialize(city);
        } else {
            alert('End editing');
        }
    }

    deleteCity(city: any) {
        var isDelete = confirm("Delete?");
        if(isDelete) {
            if (this.editingCity === null) {
                this.http
                    .deleteData("/city/" + city.id)
                    .subscribe(() => {
                        this.getCities();
                    });
            } else {
                alert('End deleting!');
            }
        }
    }

    saveCity(temp : boolean) {
        this.errorHidden = true;
        var el = document.getElementById('error-block');
        if (temp) {
            if (this.editingCity.id) {
                this.http
                    .sendDataNoResponse("/city/" + this.editingCity.id, City.deserialize(this.editingCity))
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
                        this.getCities();
                        this.editingCity = null;
                    });
            } else {
                this.http
                    .sendDataNoResponse("/city", City.deserialize(this.editingCity))
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
                        this.getCities();
                        this.editingCity = null;
                    });
            }
        } else {
            this.editingCity = null;
        }
    }

    private getCities() {
        this.http
            .getData("/cities")
            .subscribe(data => {
                var temp = data.cities;
                var cityArray: City[] = [];
                for (var i = 0; i < temp.length; i++) {
                    cityArray.push(City.deserialize(temp[i]));
                }
                this.cities = cityArray;
            });
    }

    cancelEditing() {
        this.editingCity = null;
    }

}