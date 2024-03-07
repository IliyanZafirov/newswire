import { Routes } from '@angular/router';

import { MainContainerComponent } from './main-container/main-container.component';

export const routes: Routes = [
  { path: '', redirectTo: '/articles', pathMatch: 'full' }, 
  { path: 'articles', component: MainContainerComponent }, 
];