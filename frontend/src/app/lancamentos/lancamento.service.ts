import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DatePipe } from '@angular/common';

export interface LancamentoFiltro{
  descricao: string;
  dataVencimentoInicio?: Date;
  dataVencimentoFim?: Date;
}

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  lancamentoUrl = 'http://localhost:8080/launchings';

  constructor(private http: HttpClient,
              private datePipe : DatePipe) { }

  pesquisar(filtro : LancamentoFiltro): Promise<any> {

    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    let params = new HttpParams();

    if (filtro.descricao){
      params = params.set('description',filtro.descricao);
    }

    if(filtro.dataVencimentoInicio){
      params = params.set('dueDateOf', this.datePipe.transform(filtro.dataVencimentoInicio,'yyyy-MM-dd')!);
    }

    if(filtro.dataVencimentoFim){
      params = params.set('dueDateUntil', this.datePipe.transform(filtro.dataVencimentoFim,'yyyy-MM-dd')!);
    }

    return this.http.get(`${this.lancamentoUrl}?resume`, {headers,params})
      .toPromise()
      .then((response : any) => response['content']);
  }

}
