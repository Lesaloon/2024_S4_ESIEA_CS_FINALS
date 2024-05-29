export interface Material {
	id: number;
	name: string;
	description: string;
	photo: string; // une représentation en base64
	quantity: number;
	unit: string; // gramme, litre, pièce, ...
}
