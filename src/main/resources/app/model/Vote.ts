export class Vote {
	private reason: string;
	private userId: number;
	private name: string;
	
	constructor(reason: string, userId: number, name: string) {
		this.reason = reason;
		this.userId = userId;
		this.name = name;
	}
}