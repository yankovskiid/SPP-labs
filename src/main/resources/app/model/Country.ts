import { ShortCountry } from "./ShortCountry"

export class Country extends ShortCountry {
	private id: number;


	constructor(name: string, id: number) {
		super(name);
		this.id = id;
	}
}