package br.com.drogaria.teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("Descrição E");
		Fabricante f2 = new Fabricante();
		f2.setDescricao("Descrição F");
		Fabricante f3 = new Fabricante();
		f3.setDescricao("Descrição G");
		Fabricante f4 = new Fabricante();
		f4.setDescricao("Descrição H");
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
		dao.salvar(f2);
		dao.salvar(f3);
		dao.salvar(f4);

	}

	@Test
	@Ignore
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();
		for (Fabricante fabricante : fabricantes) {
			System.out.println(fabricante);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(10L);

		Fabricante f2 = dao.buscarPorCodigo(12L);

		System.out.println(f1);
		System.out.println(f2);

	}

	@Test
	@Ignore
	public void excluir() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante fabricante = dao.buscarPorCodigo(10L);
		if (fabricante != null) {
			dao.excluir(fabricante);
		}
	}

	// @Test
	// public void excluirPorCodigo() {
	// FabricanteDAO dao = new FabricanteDAO();
	//
	// Fabricante fabricante = dao.buscarPorCodigo(10L);
	// if (fabricante != null) {
	// dao.excluir(13L);
	// }
	// }
	
	@Test
	@Ignore
	public void editar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(11L);
		fabricante.setDescricao("DESCRICAO OUTRA");

		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricante);
	}
}
