import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent {

  @Input() isActivated: boolean = false;
  @Input() title: string = 'Êtes-vous sûr de vouloir continuer ?';
  @Input() buttonText: string = 'Envoyer';
  @Input() closeButtonText: string = 'Fermer et annuler';
  @Input() sendButtonColor: string = 'danger';
  @Input() closeButtonColor: string = 'secondary';
  @Input() sendButtonAction: Function = () => {};

  send(){
    this.isActivated = false;
    this.sendButtonAction();
  }

  close(){
    this.isActivated = false;
  }
}
