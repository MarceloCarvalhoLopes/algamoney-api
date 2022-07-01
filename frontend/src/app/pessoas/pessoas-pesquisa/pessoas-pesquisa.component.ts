import { LazyLoadEvent } from 'primeng/api';
import { PessoaFiltro, PessoaService } from './../pessoa.service';
import { Component} from '@angular/core';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent  {

  filtro = new PessoaFiltro();
  pessoas = [];
  totalRegistros = 0;

  constructor(private pessoaService : PessoaService ){}

  ngOnInit(): void {
    //this.pesquisar();
  }

  pesquisar(pagina = 0) : void{
    this.filtro.pagina = pagina;

    this.pessoaService.pesquisar(this.filtro)
      .then((resultado : any) => {
        this.totalRegistros = resultado.total;
        this.pessoas = resultado.pessoas;
      });

  }

  aoMudarPagina( event : LazyLoadEvent){
    const pagina  = event!.first! / event!.rows!;
    this.pesquisar(pagina);
  }

}
