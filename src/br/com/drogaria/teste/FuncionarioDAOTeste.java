package br.com.drogaria.teste;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTeste {

	@Test
	@Ignore
	public void salvar() {
		Funcionario f1 = new Funcionario();
		f1.setNome("Angelo");
		f1.setCpf("04469834971");
		f1.setFuncao("PROGRAMADOR");
		f1.setSenha("1234");
		Funcionario f2 = new Funcionario();
		f2.setNome("Arthur");
		f2.setCpf("77769834456");
		f2.setFuncao("PROGRAMADOR 2");
		f2.setSenha("4321");
		Funcionario f3 = new Funcionario();
		f3.setNome("Rogerio");
		f3.setCpf("99999999913");
		f3.setFuncao("PROGRAMADOR MASTER");
		f3.setSenha("2255");
		Funcionario f4 = new Funcionario();
		f4.setNome("Kleber");
		f4.setCpf("123456789321");
		f4.setFuncao("DIGITADOR");
		f4.setSenha("5522");
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(f1);
		dao.salvar(f2);
		dao.salvar(f3);
		dao.salvar(f4);

	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario);
		}
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();

		Funcionario f1 = dao.buscarPorCodigo(30L);

		Funcionario f2 = dao.buscarPorCodigo(31L);

		System.out.println(f1);
		System.out.println(f2);

	}

	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();

		Funcionario funcionario = dao.buscarPorCodigo(33L);
		if (funcionario != null) {
			dao.excluir(funcionario);
		}
	}

	// @Test
	// public void excluirPorCodigo() {
	// FuncionarioDAO dao = new FuncionarioDAO();
	//
	// Funcionario Funcionario = dao.buscarPorCodigo(10L);
	// if (Funcionario != null) {
	// dao.excluir(13L);
	// }
	// }
	
	@Test
	@Ignore
	public void editar() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(32L);
		funcionario.setNome("Edgar");
		funcionario.setCpf("333333333333");
		funcionario.setFuncao("GERENTE");
		funcionario.setSenha("9999");
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.editar(funcionario);
	}
	
	@Test
	public void autenticar(){
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		
		Funcionario funcionario = funcionarioDAO.autenticar("777.698.344.56", "1234");
		
		Assert.assertNotNull(funcionario);
	}
}
