import { Component, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { delay, distinctUntilChanged, finalize, map } from 'rxjs';
import { EmployeeDialogComponent } from './employee-dialog/employee-dialog.component';
import { EmployeeService } from './employee.service';
import { EmployeeRequest } from './interfaces/request';
import { EmployeeResponse } from './interfaces/response';

type Columns = keyof EmployeeRequest | string;

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
})
export class EmployeeComponent {
  dataSource: MatTableDataSource<EmployeeResponse> = new MatTableDataSource();
  columns: Columns[] = ['id', 'name', 'age', 'actions'];
  displayColumns: string[] = ['#', 'Name', 'Age', 'Actions'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  loading = false;
  formFilter: FormGroup;

  get filter(): AbstractControl {
    return this.formFilter.controls['filter'];
  }

  constructor(
    private readonly service: EmployeeService,
    private readonly fb: FormBuilder,
    private readonly matDialog: MatDialog
  ) {
    this.formFilter = this.fb.group({
      filter: this.fb.control(''),
    });

    this.filter.valueChanges.pipe(distinctUntilChanged()).subscribe((value) => {
      this.onFilter(value);
    });
  }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  onRefresh(): void {
    this.getAllEmployees();
  }

  onFilter(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onAdd(): void {
    const dialog = this.matDialog.open(EmployeeDialogComponent, {
      data: {
        mode: 'Add',
        Employee: null,
      },
    });

    dialog.afterClosed().subscribe((data: EmployeeRequest) => {
      if (data) {
        this.add(data);
      }
    });
  }

  onEdit(employee: EmployeeResponse): void {
    const request: EmployeeRequest = {
      name: employee.name,
      age: employee.age,
    };

    const dialog = this.matDialog.open(EmployeeDialogComponent, {
      data: {
        mode: 'Edit',
        employee: request,
      },
    });

    dialog.afterClosed().subscribe((data: EmployeeRequest) => {
      if (data) {
        this.edit(employee.id, data);
      }
    });
  }

  private getAllEmployees() {
    this.loading = true;
    this.service
      .getAll()
      .pipe(
        delay(3000),
        map((data) => {
          return data._embedded.employeeResponseList;
        }),
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe((Employees) => {
        this.dataSource = new MatTableDataSource(Employees);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
  }

  private add(Employee: EmployeeRequest) {
    this.loading = true;
    this.service
      .create(Employee)
      .pipe(
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe((data) => {
        this.dataSource.data = [...this.dataSource.data, data];
      });
  }

  private edit(id: number, Employee: EmployeeRequest) {
    this.loading = true;
    this.service
      .update(id, Employee)
      .pipe(
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe((data) => {
        const index = this.dataSource.data.findIndex((r) => r.id === data.id);
        this.dataSource.data[index] = data;
      });
  }
}
