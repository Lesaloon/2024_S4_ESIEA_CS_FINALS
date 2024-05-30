import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { ApiResponse } from '../api-response';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  static URL = "http://localhost:8080/tutoapi/api/"

  constructor(@Inject(HttpClient) private http: HttpClient) { }

  // warning : this way not work, since the result is returned before the data is fetched so it may be empty.
  //
  get<T>(url: string): Observable<T[]> {
    return this.http.get(HttpClientService.URL + url).pipe(
      map((response: Object) => {
        const apiResponse = response as ApiResponse<T>;
        if (apiResponse.success) {
          return apiResponse.payload;
        } else {
          console.error(apiResponse.message);
          console.error(apiResponse.errors);
          return [];
        }
      })
    );
  }

  post<T>(url: string, data: any) : Observable<T[]> {
    return this.http.post( HttpClientService.URL + url, data).pipe(
      map((response: Object) => {
        const apiResponse = response as ApiResponse<T>;
        if (apiResponse.success) {
          return apiResponse.payload;
        } else {
          console.error(apiResponse.message);
          console.error(apiResponse.errors);
          return [];
        }
      })
    );
  }

  put<T>(url: string, data: any) : Observable<T[]> {
    return this.http.put( HttpClientService.URL + url, data).pipe(
      map((response: Object) => {
        const apiResponse = response as ApiResponse<T>;
        if (apiResponse.success) {
          return apiResponse.payload;
        } else {
          console.error(apiResponse.message);
          console.error(apiResponse.errors);
          return [];
        }
      })
    );
  }

  delete<T>(url: string)  : Observable<T[]>  {
    return this.http.delete( HttpClientService.URL + url).pipe(
      map((response: Object) => {
        const apiResponse = response as ApiResponse<T>;
        if (apiResponse.success) {
          return apiResponse.payload;
        } else {
          console.error(apiResponse.message);
          console.error(apiResponse.errors);
          return [];
        }
      })
    );
  }
}
