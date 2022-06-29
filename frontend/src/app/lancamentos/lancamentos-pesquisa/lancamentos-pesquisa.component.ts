import { LancamentoService, LancamentoFiltro } from './../lancamento.service';
import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})
export class LancamentosPesquisaComponent implements OnInit  {

  filtro = new LancamentoFiltro();
  lancamentos = [];
  totalRegistros = 0;

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
}
