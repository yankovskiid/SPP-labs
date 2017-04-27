export class UserInformation {
     username: string;
     surname: string;
     city: string;

    constructor() {
    }

    static deserialize(input: any): UserInformation {
	    var res = new UserInformation();
	    res.username = input.username;
	    res.surname = input.surname;
	    res.city = input.city;
	    return res;
    }
}