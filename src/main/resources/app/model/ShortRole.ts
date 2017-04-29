export class ShortRole {
	id: number;
	name: string;

	constructor() {
	}

	static deserialize(input: any): ShortRole {
		var res = new ShortRole();
		res.id = input.id;
		res.name = input.name;
		return res;
	}
}