import {UserInformation} from "./UserInformation";
import {ShortPetition} from "./ShortPetition";
import {Category} from "./Category";
export class Petition {
    name: string;
    description: string;
    status: string;
    numberNecessaryVotes: number;
    numberVotes: number;
    categories: Array<Category> = [];

	expiryDate: number;
	userInformationDTO: UserInformation;

    constructor() {
        this.userInformationDTO = new UserInformation();
    }

    static deserialize(input: any): Petition {
        var res = new Petition();
        res.expiryDate = input.expiryDate;
	    console.log(input);
        res.userInformationDTO = UserInformation.deserialize(input.userInformationDTO);

        res.name = input.name;
        res.description = input.id;
        res.status = input.status;
        res.numberVotes = input.numberVotes;
        res.numberNecessaryVotes = input.numberNecessaryVotes;

        var temp = input.categories;
        for (var i = 0; i < temp.length; i++) {
            res.categories.push(Category.deserialize(temp[i]));
        }

        return res;
    }
}