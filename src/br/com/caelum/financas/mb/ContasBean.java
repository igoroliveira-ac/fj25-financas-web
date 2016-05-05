package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ContasBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

	private Conta conta = new Conta();
	private List<Conta> contas;
	
	@Inject
	private ContaDao dao;
	
	public void grava() {
		if(this.conta.getId() == null){
			System.out.println("Gravando a conta");
			dao.adiciona(this.conta);
			this.contas.add(this.conta);
		}else{
			System.out.println("Alterando a conta");
			dao.altera(conta);
			this.contas.remove(this.conta);
			this.contas.add(conta);
		}
		limpaFormularioDoJSF();
	}
	
	public List<Conta> getContas() {
		if(contas == null){
			System.out.println("Listando as contas");
			this.contas = dao.lista();
		}
		return contas;
	}

	public void remove() {
		System.out.println("Removendo a conta");
		dao.remove(this.conta);
		this.contas.remove(this.conta);
		limpaFormularioDoJSF();
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
	}
}
