import {OnInit} from "@angular/core";
import {ShortVote} from "../../../../model/ShortVote";
import {HttpService} from "../../../../services/httpServices/http.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Vote} from "../../../../model/Vote";
export class VotesComponent implements OnInit {

    private votes: ShortVote[] = [];
    private editingVote: ShortVote = null;

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute) {}

    ngOnInit(): void {

    }

    addVote() {
        if (this.editingVote === null) {
            this.editingVote = new ShortVote();
        } else {
            alert('End editing!');
        }
    }

    saveVote(temp: boolean) {
        if (temp) {
            this.activatedRoute.params.subscribe((params: Params) => {
                let petitionId = params['id'];
                this.http
                    .sendDataNoResponse("/petition/" + petitionId + "/vote", Vote.deserialize(this.editingVote))
                    .subscribe(() => {
                        this.getVotes();
                        this.editingVote = null;
                    })
            });
        } else {
            this.editingVote = null;
        }
    }

    getVotes() {

    }
}