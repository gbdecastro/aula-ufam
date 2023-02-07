import { NoopScrollStrategy } from '@angular/cdk/overlay';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import {
  MatDialogModule,
  MAT_DIALOG_DATA,
  MAT_DIALOG_DEFAULT_OPTIONS,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';

import { EmployeeDialogComponent } from './employee-dialog/employee-dialog.component';
import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeeComponent } from './employee.component';
import { EmployeeService } from './employee.service';
import { LoadingComponent } from './loading/loading.component';

@NgModule({
  declarations: [EmployeeComponent, EmployeeDialogComponent, LoadingComponent],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSortModule,
    MatDialogModule,
    MatProgressSpinnerModule,
  ],
  providers: [
    EmployeeService,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    {
      provide: MAT_DIALOG_DEFAULT_OPTIONS,
      useValue: {
        hasBackdrop: true,
        panelClass: 'portal-dialog',
        height: 'auto',
        width: '900px',
        disableClose: true,
        useValue: { scrollStrategy: new NoopScrollStrategy() },
      },
    },
  ],
})
export class EmployeeModule {}
