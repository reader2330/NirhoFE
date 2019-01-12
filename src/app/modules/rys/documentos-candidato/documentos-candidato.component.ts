import { Component, OnInit } from '@angular/core';
import {ReclutamientoService} from '../services/reclutamiento.service';
import Swal from "sweetalert2";

@Component({
  selector: 'app-documentos-candidato',
  templateUrl: './documentos-candidato.component.html',
  styleUrls: ['./documentos-candidato.component.scss']
})
export class DocumentosCandidatoComponent implements OnInit {
  candidatos = [];
  candidato = {};
  nameFile = '';
  documentos = [];
  imageSrc: string = '';
  candidatoActivo = '';
  blockSelect = false;
  constructor(private Reclutamiento: ReclutamientoService) {
  }

  ngOnInit() {
    this.candidatoActivo = sessionStorage.getItem('candidatoActive');
    this.Reclutamiento.getCandidatos().subscribe(res => {
      console.log(res);
      this.candidatos = res;
      for ( let cand of this.candidatos) {
        if (cand.nombre === this.candidatoActivo) {
          this.blockSelect = true;
          this.candidato = cand;
          this.getDocumentosByCandidato();
        }
      }
    });
  }

  handleInputChange(e) {
    console.log(e);
    var file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    var pattern = /pdf/;
    console.log(file);
    var reader = new FileReader();
    if (!file.type.match(pattern)) {
      Swal(
        'Lo siento!.',
        'El formato debe ser pdf',
        'warning'
      );
      e.target.value = '';
      return;
    }
    if ((((file.size / 1024) / 1024) > 1)) {
      Swal(
        'Lo siento!',
        'El tamaño no debe superar 1 MB',
        'warning'
      );
      e.target.value = '';
      return;
    }
    this.nameFile = file.name;
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }

  _handleReaderLoaded(e) {
    let reader = e.target;
    this.imageSrc = reader.result;
    let json = {
      id: null,
      idCandidato: this.candidato['id'],
      nombre: this.nameFile,
      file: this.imageSrc
    };
    this.Reclutamiento.saveDocument(json).subscribe(res => {
      this.getDocumentosByCandidato();
    });

  }

  getDocumentosByCandidato() {
    this.Reclutamiento.getDocumentosByCandidato(this.candidato['id']).subscribe(res => {
      console.log(res);
      this.documentos = res;
    });
  }

  descargarArchivo(doc) {
    this.Reclutamiento.getDocumentoByID(doc.id);
  }

  eliminarArchivo(doc) {
    Swal({
      title: '',
      text: 'Seguro que quieres eliminar el documento',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si eliminar',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.Reclutamiento.deleteDocument(doc.id).subscribe(res => {
          Swal(
            'Listo.',
            'El documento ha sido eliminado',
            'success'
          );
          this.getDocumentosByCandidato();
        });
      }
    });
  }
}
