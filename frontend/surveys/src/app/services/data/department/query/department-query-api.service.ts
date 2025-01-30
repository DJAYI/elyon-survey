import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpResponse } from '../../../../model/http-response';
import { DepartmentEntity } from '../../../../model/survey';

@Injectable({
  providedIn: 'root'
})
export class DepartmentQueryApiService {
  host = 'http://localhost:8080/api/v1/departments';

  constructor(private http: HttpClient) { }

  public getDepartments() {
    return this.http.get<HttpResponse<DepartmentEntity[]>>(`${this.host}`, {
      withCredentials: true
    });
  }

  public getDepartmentById(id: number) {
    return this.http.get<HttpResponse<DepartmentEntity>>(`${this.host}/${id}`, {
      withCredentials: true
    });
  }
}
