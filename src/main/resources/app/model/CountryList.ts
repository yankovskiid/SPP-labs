import { Country } from "./Country"

export class CountryList {
	private countries: Array<Country>;
	
	constructor(countries: Array<Country>) {
		this.countries = countries;
	}
}