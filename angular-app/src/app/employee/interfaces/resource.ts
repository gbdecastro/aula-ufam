import { Resource } from './hateoas';
import { EmployeeResponse } from './response';

export interface EmployeeResource extends EmployeeResponse, Resource {}

export interface EmployeeResourceList extends Resource {
  _embedded: {
    employeeResponseList: EmployeeResponse[];
  };
}
