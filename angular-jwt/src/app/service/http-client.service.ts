import { Injectable } from '@angular/core';
import {User} from '../user';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  tokenStr: string ;
  constructor(private httpClient: HttpClient) { }

  getUsers()
  {
    this.tokenStr  = sessionStorage.getItem('token');
    console.log("getUsers test call");
    const headers = new HttpHeaders({ Authorization: 'Bearer ' +this.tokenStr });
    return this.httpClient.get<User[]>('http://localhost:8080/users',{headers});
  }
}
