import { Component, Input, signal, WritableSignal } from '@angular/core';
import { ChangeDetectionStrategy } from '@angular/core';
@Component({
  selector: 'app-modal',
  standalone: true,
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ModalComponent {

  constructor() {}
  @Input()
  isActive: WritableSignal<boolean> = signal(false);
  // @Input() title: string = 'Êtes-vous sûr de vouloir continuer ?';
  // @Input() buttonText: string = 'Envoyer';
  // @Input() closeButtonText: string = 'Fermer et annuler';
  // @Input() sendButtonColor: string = 'danger';
  // @Input() closeButtonColor: string = 'secondary';
  // @Input() sendButtonAction: Function = () => {};

  send(){
    this.isActive.set(false);
  }

  close(){
    this.isActive.set(false);
  }

  effect(){
    console.log('effect');
    this.isActive.set(true);
  }
}
