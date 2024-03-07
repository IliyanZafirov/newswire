import { Routes } from '@angular/router';

import { HeaderComponent } from './header/header.component';

export const routes: Routes = [
  { path: '', redirectTo: '/articles', pathMatch: 'full' }, 
  { path: 'articles', component: HeaderComponent }, 
];