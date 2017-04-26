export class RegistrationUser {

	public email : String;
	public password : String;
	public repeatPassword : String;
	public nick : String;

	constructor(email: String, password: String, repeatPassword: String, nick: String) {
		this.email = email;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.nick = nick;
	}
}
