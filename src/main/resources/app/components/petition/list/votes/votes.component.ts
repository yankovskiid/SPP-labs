import {Component, OnInit} from "@angular/core";
import {ShortVote} from "../../../../model/ShortVote";
import {HttpService} from "../../../../services/httpServices/http.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Vote} from "../../../../model/Vote";
import {AuthenticationService} from "../../../../services/httpServices/authenticationServices/authentication.service";
@Component({
    selector: '[votes]',
    templateUrl: 'app/components/petition/list/votes/votes.component.html'
})
export class VotesComponent implements OnInit {

    private votes: ShortVote[] = [];
    private votesCount: number = 0;
    private editingVote: ShortVote = null;

    constructor(private http: HttpService, private activatedRoute: ActivatedRoute, private authService: AuthenticationService) {}

    ngOnInit(): void {
        this.getVotesCount();
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
                        this.getVotesCount();
                        this.editingVote = null;
                    })
            });
        } else {
            this.editingVote = null;
        }
    }

    getVotesCount() {
        this.activatedRoute.params.subscribe((params: Params) => {
            let petitionId = params['id'];
            this.http
                .getData("/petition/" + petitionId + "/votesCount")
                .subscribe(data => {
                    this.votesCount = data;
                });
        })
    }
}