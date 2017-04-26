import {UserInformation} from "./UserInformation";
import {ShortPetition} from "./ShortPetition";
import {Category} from "./Category";
export class Petition extends ShortPetition {
    private expiryDate: number;
    private userInformationDTO: UserInformation;

    constructor(name: string, description: string, status: string, numberNecessaryVotes: number, numberVotes: number, categories: Array<Category>, expiryDate: number, userInformationDTO: UserInformation) {
        super(name, description, status, numberNecessaryVotes, numberVotes, categories);
        this.expiryDate = expiryDate;
        this.userInformationDTO = userInformationDTO;
    }
}