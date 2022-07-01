import { Component, OnInit, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';

import { LancamentoService, LancamentoFiltro } from './../lancamento.service';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent implements OnInit  {

  filtro = new LancamentoFiltro();

  totalRegistros = 0;

  lancamentos = [];
  @ViewChild('tabela') grid!: Table;
  constructor(private lancamentoService: LancamentoService){}

  ngOnInit(): void {
    //this.pesquisar();
  }

  pesquisar(pagina = 0): void{
    this.filtro.pagina = pagina;

    this.lancamentoService.pesquisar(this.filtro)
      .then((resultado : any ) => {
        this.totalRegistros = resultado.total;
        this.lancamentos = resultado.lancamentos;
      });
  }

  aoMudarPagina(event: LazyLoadEvent) {
    //console.log(event);
    const pagina = event!.first! / event!.rows!;
    this.pesquisar(pagina);

  }

  excluir(lancamento: any){
    this.lancamentoService.excluir(lancamento.id)
    .then(() => {
      //console.log('Excluindo');
      this.grid.reset();
    });
  }

}
