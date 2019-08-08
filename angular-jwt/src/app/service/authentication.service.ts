import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginRequest } from './LoginRequest' ;

import { from } from 'rxjs';


export class AuthResponse{
  jwt : string ;

  constructor(token : string){
    this.jwt = token ;
  }
}
@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  logRequest: LoginRequest;
  
  constructor(private httpClient : HttpClient) { }

  authenticate(username, password) {
    const headers = new HttpHeaders({ 'Access-Control-Allow-Origin' : '*' });
     this.logRequest = new LoginRequest(username,password);
     console.log('sigin in: '+ this.logRequest)
    return this.httpClient.post<AuthResponse>('http://localhost:8080/signin',this.logRequest,{headers}).pipe(
     map(
      userData => {
       console.log('received token '+userData.jwt);
        let tokenStr= 'Bearer '+ userData.jwt;
        sessionStorage.setItem('token', tokenStr);
        return userData;
       }
     )

    );
      }
  isUserLoggedIn() {
    let token = sessionStorage.getItem('token')
    console.log(!(token === null))
    return !(token === null)
  }

  logOut() {
    sessionStorage.removeItem('token')
  }
}
