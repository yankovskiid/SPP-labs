import {City} from "./City";
export class CityList {
    private cities: Array<City> = [];

    constructor(cities: Array<City>) {
        this.cities = cities;
    }
}