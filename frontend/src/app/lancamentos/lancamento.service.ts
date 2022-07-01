import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DatePipe } from '@angular/common';

export class LancamentoFiltro{
  descricao: string = '';
  dataVencimentoInicio?: Date;
  dataVencimentoFim?: Date;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  lancamentoUrl = 'http://localhost:8080/launchings';

  constructor(private http: HttpClient,
              private datePipe : DatePipe) { }

  pesquisar(filtro : LancamentoFiltro): Promise<any> {

    let headers = new HttpHeaders();
    let params = new HttpParams();

    headers = headers.set('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    params = params.set('page', filtro.pagina);
    params = params.set('size', filtro.itensPorPagina);

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
      .then((response : any) => {
        const lancamentos = response['content'];

        const resultado = {
          lancamentos,
          total: response['totalElements']
        };

        return resultado;

      });

  }

  excluir(id: number): Promise<void>{
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete<void>(`${this.lancamentoUrl}/${id}`, { headers })
    .toPromise();

  }

}
