export class Role {
	private name: string;

	static deserialize(input: any): Role {
		var res = new Role();
		res.name = input.name;
		return res;
	}
}