import { ShortRole } from "./ShortRole"

export class RoleList {
	private roles: Array<ShortRole>;
	
	constructor(roles: Array<ShortRole>) {
		this.roles = roles;
	}
}