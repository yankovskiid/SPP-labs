import { MainComponent } from './components/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
import { NotFoundComponent } from "./components/error/not-found.component";

const appRoutes: Routes = [
	{
		path: '',
		component: MainComponent
	},
	{
		path: '**',
		component: NotFoundComponent
	}
];
@NgModule({
	imports: [
		RouterModule.forRoot(appRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AppRoutingModule {}