export interface Audit {
  createdAt: string;
  updatedAt: string;
}

export interface EmployeeResponse extends Audit {
  id: number;
  name: string;
  age: number;
}
