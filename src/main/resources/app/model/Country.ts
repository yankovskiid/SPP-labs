import { ShortCountry } from "./ShortCountry"

export class Country extends ShortCountry {
	id: number;


	constructor() {
		super();
	}

	static deserialize(data: any): Country {
		var res = new Country();
		res.id = data.id;
		res.name = data.name;

		return res;
	}
}