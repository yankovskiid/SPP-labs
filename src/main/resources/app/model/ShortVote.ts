import {UserInformation} from "./UserInformation";
export class ShortVote {
	 reason: string;
	 user: UserInformation;

	constructor() {
	}

	static deserialize(input: any): ShortVote {
		var res = new ShortVote();
		res.reason = input.reason;
		res.user = input.user;
		return res;
	}
}