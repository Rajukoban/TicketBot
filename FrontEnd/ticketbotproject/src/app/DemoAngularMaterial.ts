import { NgModule } from "@angular/core";
import {MatButtonModule }from '@angular/material/button';
import {MatCardModule}from '@angular/material/card';
import {MatInputModule}from '@angular/material/input';
import {MatToolbarModule}from '@angular/material/toolbar';
import {MatSelectModule}from '@angular/material/select';
import {MatError, MatFormFieldControl, MatFormFieldModule}from '@angular/material/form-field';
import {MatIconModule}from '@angular/material/icon';
import {MatProgressSpinnerModule}from '@angular/material/progress-spinner';
import {MatSnackBarModule}from '@angular/material/snack-bar';
import { MatChipsModule}from '@angular/material/chips';
import {MatPaginatorModule}from '@angular/material/paginator';
import {MatMenuModule}from '@angular/material/menu';
import {MatRadioModule}from '@angular/material/radio';
import {MatDividerModule}from '@angular/material/divider';
import {MatDatepickerModule}from '@angular/material/datepicker';
import {MatNativeDateModule}from '@angular/material/core';
import { MatDialogModule}from '@angular/material/dialog';
import {MatTableModule}from '@angular/material/table';
import { MatListModule } from "@angular/material/list";
import { MatSidenavModule } from "@angular/material/sidenav";

@NgModule({
    exports:[
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatToolbarModule,
        MatSelectModule,
        MatIconModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        MatSnackBarModule,
        MatChipsModule,
        MatPaginatorModule,
        MatMenuModule,
        MatTableModule,
        MatDialogModule,
        MatNativeDateModule,
        MatRadioModule,
        MatDividerModule,
        MatDatepickerModule,
        MatListModule,
        MatSidenavModule
       
    ]
})

export class DemoAngularMaterial{

}