import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { User } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class AccountService {
    private userSubject: BehaviorSubject<User | null>;
    public user: Observable<User | null>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
        this.user = this.userSubject.asObservable();
    }

    public get userValue() {
        return this.userSubject.value;
    }

    login(username: string, password: string): Observable<User> {
        const userDto = { username, password };

        return this.http.post<User>(`${environment.apiUrl}/api/login`, userDto)
            .pipe(
                map(user => {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('user', JSON.stringify(user));
                    this.userSubject.next(user);
                    return user;
                }),
                catchError((error: any) => {
                    // Handle errors here
                    let errorMessage = 'User is not authorised.';
                    
                    if (error && error.error && error.error.message) {
                        // If the server returns an error message, use it
                        errorMessage = error.error.message;
                    }

                    // You can log the error or perform additional error handling as needed

                    // Re-throw the error to propagate it to the component
                    return throwError(errorMessage);
                })
            );
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('user');
        this.userSubject.next(null);
        this.router.navigate(['/account/login']);
    }

    register(user: User): Observable<any> {
        return this.http.post(`${environment.apiUrl}/api/register`, user)
            .pipe(
                catchError((error: any) => {
                    if (error && error.status === 201) {
                        // If the server responds with a 201 status code (Created), return a success response
                        return of('Registration successful');
                    }
    
                    let errorMessage = 'An error occurred during registration.';
    
                    if (error && error.error && error.error.errors && Array.isArray(error.error.errors)) {
                        // If the server returns an "errors" array, join error messages into a single string
                        errorMessage = error.error.errors.join('\n');
                    }
    
                    return throwError(errorMessage);
                })
            );
    }
    
    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    getById(id: string) {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    update(id: string, params: any) {
        return this.http.put(`${environment.apiUrl}/users/${id}`, params)
            .pipe(map(x => {
                // update stored user if the logged in user updated their own record
                if (id == this.userValue?.id) {
                    // update local storage
                    const user = { ...this.userValue, ...params };
                    localStorage.setItem('user', JSON.stringify(user));

                    // publish updated user to subscribers
                    this.userSubject.next(user);
                }
                return x;
            }));
    }

    delete(id: string) {
        return this.http.delete(`${environment.apiUrl}/users/${id}`)
            .pipe(map(x => {
                // auto logout if the logged in user deleted their own record
                if (id == this.userValue?.id) {
                    this.logout();
                }
                return x;
            }));
    }
}
