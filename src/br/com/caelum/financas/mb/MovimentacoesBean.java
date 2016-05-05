package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.CategoriaDao;
import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@Named
@ViewScoped
public class MovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Movimentacao> movimentacoes;
	private Movimentacao movimentacao = new Movimentacao();
	private Integer contaId;
	private Integer categoriaId;
	private List<Categoria> categorias;
	
	@Inject
	private CategoriaDao categoriaDao;
	@Inject
	private MovimentacaoDao dao;
	@Inject
	private ContaDao contaDao;
	
	public void grava() {
		if(this.movimentacao.getId() == null){
			System.out.println("Fazendo a gravacao da movimentacao");
			Conta contaRelacionada = contaDao.busca(this.contaId);
			this.movimentacao.setConta(contaRelacionada);
			dao.adiciona(this.movimentacao);
			this.movimentacoes.add(this.movimentacao);
		}else{
			System.out.println("Fazendo a alteração da movimentacao");
			Conta contaRelacionada = contaDao.busca(this.contaId);
			this.movimentacao.setConta(contaRelacionada);
			dao.altera(this.movimentacao);
			this.movimentacoes.remove(this.movimentacao);
			this.movimentacoes.add(this.movimentacao);
		}
		limpaFormularioDoJSF();
	}
	
	public List<Movimentacao> getMovimentacoes() {
		if(this.movimentacoes == null){
			System.out.println("Listando as movimentações");
			this.movimentacoes = dao.lista();
			//this.movimentacoes = dao.listaComCategorias();
		}
		return movimentacoes;
	}

	public void remove() {
		System.out.println("Removendo a movimentacao");
		dao.remove(this.movimentacao);
		this.movimentacoes.remove(this.movimentacao);
		limpaFormularioDoJSF();
	}
	
	public Movimentacao getMovimentacao() {
		if(movimentacao.getData()==null) {
			movimentacao.setData(Calendar.getInstance());
		}
		return movimentacao;
	}
	
	public void adicionaCategoria(){
		if(this.categoriaId != null && this.categoriaId > 0){
			Categoria categoria = categoriaDao.procura(this.categoriaId);
			this.movimentacao.getCategorias().add(categoria);
		}
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}


	public List<Categoria> getCategorias() {
		if(this.categorias == null){
			System.out.println("Listando as categorias");
			this.categorias = this.categoriaDao.lista();
		}
		return this.categorias;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}
	
}
