import { Routes } from '@angular/router';

import { MainContainerComponent } from './main-container/main-container.component';
import { ArticleDetailsComponent } from './article-details/article-details.component';
import { AppComponent } from './app.component';
import { ArticlePostComponent } from './article-post/article-post.component';

export const routes: Routes = [
  { path: '',   redirectTo: '/articles', pathMatch: 'full' },
  { path: 'articles', component: MainContainerComponent },
  { path: 'create-article', component:ArticlePostComponent },
  { path: 'articles/:id', component: ArticleDetailsComponent },
];