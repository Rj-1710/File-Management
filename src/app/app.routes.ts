import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FileDashboardComponent } from './file-dashboard/file-dashboard.component';
import { FileCreateComponent } from './file-create/file-create.component';
import { FileDetailsComponent } from './file-details/file-details.component';
import { FileSearchComponent } from './file-search/file-search.component';
import { BatchRunnerComponent } from './batch-runner/batch-runner.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'file-dashboard', component: FileDashboardComponent },
  { path: 'create-file', component: FileCreateComponent },
  { path: 'file-details', component: FileDetailsComponent },
  {path: 'file-search', component:FileSearchComponent},
  {path:'batch-runner',component:BatchRunnerComponent},
  { path: '**', redirectTo: '' }
];
