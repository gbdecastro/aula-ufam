import { Component, Inject } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmployeeRequest } from '../interfaces/request';

@Component({
  selector: 'app-employee-dialog',
  templateUrl: './employee-dialog.component.html',
  styleUrls: ['./employee-dialog.component.scss'],
})
export class EmployeeDialogComponent {
  form: FormGroup;

  get mode(): string {
    return this.data.mode;
  }

  get name(): AbstractControl {
    return this.form.controls['name'];
  }

  get age(): AbstractControl {
    return this.form.controls['age'];
  }

  constructor(
    @Inject(MAT_DIALOG_DATA)
    private readonly data: {
      mode: string;
      employee: EmployeeRequest | null;
    },
    private readonly dialogRef: MatDialogRef<EmployeeDialogComponent>,
    private readonly fb: FormBuilder
  ) {
    this.form = this.fb.group({
      name: this.fb.control('', [
        Validators.required,
        Validators.maxLength(255),
      ]),
      age: this.fb.control('', [Validators.required, Validators.min(18)]),
    });

    if (this.data.employee) {
      this.name.setValue(this.data.employee.name);
      this.age.setValue(this.data.employee.age);
    }
  }

  save(): void {
    if (this.form.valid) this.dialogRef.close(this.form.value);
  }
}
