package br.com.drogaria.teste;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.boot.model.relational.SimpleAuxiliaryDatabaseObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Venda;
import br.com.drogaria.filter.VendaFilter;

public class VendaDAOTeste {
	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);
		
		Venda venda = new Venda();
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValor(new BigDecimal(112.50D));
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(5L);

		System.out.println(venda);
	}

	@Test
	@Ignore
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.listar();
		System.out.println(vendas);
	}

	@Test
	@Ignore
	public void excluir() {
		VendaDAO vendaDAO = new VendaDAO();

		Venda venda = vendaDAO.buscarPorCodigo(5L);

		vendaDAO.excluir(venda);
	}

	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(1L);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscarPorCodigo(7L);
		
		venda.setFuncionario(funcionario);
		venda.setHorario(new Date());
		venda.setValor(new BigDecimal(999.50D));
		
		vendaDAO.editar(venda);
	}
	
	@Test
	public void buscar() throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");		
		
		VendaFilter filtro = new VendaFilter();
		filtro.setDataInicial(formato.parse("01/06/2016"));
		filtro.setDataFinal(formato.parse("09/06/2016"));
		
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> vendas = vendaDAO.buscar(filtro);
		
		for(Venda venda : vendas){
			System.out.println(venda);
		}
		
	}
}
