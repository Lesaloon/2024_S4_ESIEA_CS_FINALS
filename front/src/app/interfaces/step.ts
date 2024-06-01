export interface Step {
	id: number;
	title: string;
	sequenceNumber: number;
	description: string;
	photo: string; // une représentation en base64
	photoType: string; // le type de l'image ( image/png, image/jpeg, ...)
}
