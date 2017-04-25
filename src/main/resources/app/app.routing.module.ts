import { MainComponent } from './components/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

const appRoutes: Routes = [
	{
		path: '',
		component: MainComponent
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