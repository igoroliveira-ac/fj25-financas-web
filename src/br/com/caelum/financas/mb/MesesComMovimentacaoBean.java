package br.com.caelum.financas.mb;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

@Named
@RequestScoped
public class MesesComMovimentacaoBean {

	private Conta conta = new Conta();
	private List<ValorPorMesEAno> valoresPorMesEAno;
	private TipoMovimentacao tipoMovimentacao;
	
	@Inject
	private MovimentacaoDao dao;
	
	public void lista() {
		System.out.println("Listando as contas pelos valores movimentados no mes");
		this.valoresPorMesEAno = dao.listaMesesComMovimentacao(this.conta, this.tipoMovimentacao);
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Conta getConta() {
		return conta;
	}

	public List<ValorPorMesEAno> getValoresPorMesEAno() {
		return valoresPorMesEAno;
	}

}
