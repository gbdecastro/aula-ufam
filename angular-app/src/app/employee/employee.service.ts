import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeRequest } from './interfaces/request';
import { EmployeeResource, EmployeeResourceList } from './interfaces/resource';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private API_URL = 'http://localhost:8090/api/employees';

  constructor(private http: HttpClient) {}

  getAll(): Observable<EmployeeResourceList> {
    return this.http.get<EmployeeResourceList>(this.API_URL);
  }

  getById(id: number): Observable<EmployeeResource> {
    return this.http.get<EmployeeResource>(`${this.API_URL}/${id}`);
  }

  create(employee: EmployeeRequest): Observable<EmployeeResource> {
    return this.http.post<EmployeeResource>(this.API_URL, employee);
  }

  update(id: number, employee: EmployeeRequest): Observable<EmployeeResource> {
    return this.http.put<EmployeeResource>(`${this.API_URL}/${id}`, employee);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}
