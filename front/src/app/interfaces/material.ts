export interface Material {
	id: number;
	name: string;
	description: string;
	photo: string; // une représentation en base64
	photoType: string; // le type de l'image ( image/png, image/jpeg, ...)
	quantity: number;
	unit: string; // gramme, litre, pièce, ...
}
