import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeroComponent } from './components/hero/hero.component';
import { UrlRedirectGuard } from './url-redirect.guard';
import { LoadingComponent } from './components/loading/loading.component';

const routes: Routes = [
  { path: '', component: HeroComponent },
  { path: ':shortenedUrl', component: LoadingComponent, canActivate: [UrlRedirectGuard] },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
