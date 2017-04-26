export class UserInformation {
    private username: string;
    private surname: string;
    private city: string;

    constructor(username: string, surname: string, city: string) {
        this.username = username;
        this.surname = surname;
        this.city = city;
    }
}