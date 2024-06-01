import { TypeOfTutorial } from "../enum/type-of-tutorial";
import { Material } from "./material";
import { DifficultyLevel } from "../enum/difficulty-level";
import { Step } from "./step";
export interface Tutorial {
	id: number;
	title: string;
	type: TypeOfTutorial,
	description: string;
	tools: string;
	toolsPhoto: string; // une représentation en base64
	materials: Material[];
	photo: string; // une représentation en base64
	timeToComplete: number;
	cost: number;
	difficultyLevel: DifficultyLevel;
	steps: Step[];
}