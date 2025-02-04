import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../../environments/environment';
import { HttpResponse } from '../../../../schemas/http-response';
import { DepartmentEntity } from '../../../../schemas/survey';

@Injectable({
  providedIn: 'root'
})
export class DepartmentQueryApiService {
  apiUrl = environment.API_URL;


  constructor(private http: HttpClient) { }

  public getDepartments() {
    return this.http.get<HttpResponse<DepartmentEntity[]>>(`${this.apiUrl}departments`, {
      withCredentials: true
    });
  }

  public getDepartmentById(id: number) {
    return this.http.get<HttpResponse<DepartmentEntity>>(`${this.apiUrl}departments/${id}`, {
      withCredentials: true
    });
  }
}
