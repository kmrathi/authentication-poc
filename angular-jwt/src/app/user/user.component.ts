import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import {User} from '../user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private httpClientService: HttpClientService) { }

  users: User[] ;

  ngOnInit() {
    this.httpClientService.getUsers().subscribe(
      response => this.handleSuccessfulResponse(response));
  }

  handleSuccessfulResponse(response)
  {
    this.users = response;
  }
}
