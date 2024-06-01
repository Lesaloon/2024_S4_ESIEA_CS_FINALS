import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TutorialComponent } from './components/tutorial/tutorial.component';
import { StepComponent } from './components/step/step.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { CreateTutorialComponent } from './components/create-tutorial/create-tutorial.component';
import { EditTutorialComponent } from './components/edit-tutorial/edit-tutorial.component';
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
		component: CreateTutorialComponent
	},
	{
		path: "edit/:id",
		component: EditTutorialComponent
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
