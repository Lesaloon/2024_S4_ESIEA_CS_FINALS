import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';
import { StepComponent } from './components/step/step.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
export const routes: Routes = [
	{
		path: "/",
		component: HomeComponent
	},
	{
		path: "/:id",
		component: TutorialComponent
	},
	{
		path: "/:id/step/:stepId",
		component: StepComponent
	},
	{
		path: "/*",
		component: NotFoundComponent
	}
];
