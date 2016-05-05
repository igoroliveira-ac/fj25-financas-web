package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.financas.modelo.Categoria;

@Stateless
public class CategoriaDao {

	@PersistenceContext
	private EntityManager manager;
	
	public Categoria procura(Integer categoriaId) {
		return this.manager.find(Categoria.class, categoriaId);
	}

	public List<Categoria> lista() {
		String jpql = "select c from Categoria c";
		return this.manager.createQuery(jpql, Categoria.class).getResultList();
	}

}
