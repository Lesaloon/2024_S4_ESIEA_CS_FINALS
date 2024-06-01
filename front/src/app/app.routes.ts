import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';
import { StepComponent } from './components/step/step.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CreateAndEditTutorialComponent } from './components/create-and-edit-tutorial/create-and-edit-tutorial.component';
export const routes: Routes = [
	{
		path: "",
		component: HomeComponent
	},
	{
		path: "home",
		component: HomeComponent
	},
	{
		path: "create",
		component: CreateAndEditTutorialComponent
	},
	{
		path: "edit/:id",
		component: CreateAndEditTutorialComponent
	},
	{
		path: "tutorial/:id",
		component: TutorialComponent
	},
	{
		path: "tutorial/:id/step/:stepId",
		component: StepComponent
	},
	{
		path: "**",
		component: NotFoundComponent
	}
];
