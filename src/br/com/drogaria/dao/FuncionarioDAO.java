package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.HibernateUtil;

public class FuncionarioDAO {

	public void salvar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(funcionario);
			transacao.commit();

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			ex.printStackTrace();
			throw ex;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Funcionario> funcionario = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionario = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcionario;
	}

	public Funcionario buscarPorCodigo(Long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcionario;
	}

	public void excluir(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(funcionario);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			ex.printStackTrace();

		} finally {
			sessao.close();
		}
	}

	// public void excluir(Long codigo) {
	// Session sessao = HibernateUtil.getSessionFactory().openSession();
	// Transaction transacao = null;
	//
	// try {
	// transacao = sessao.beginTransaction();
	// Fabricante fabricante = buscarPorCodigo(codigo);
	// sessao.delete(fabricante);
	// transacao.commit();
	// } catch (RuntimeException ex) {
	// if (transacao != null) {
	// transacao.rollback();
	// }
	// ex.printStackTrace();
	//
	// } finally {
	// sessao.close();
	// }
	// }

	public void editar(Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();

			// Fabricante fabricanteEditar =
			// buscarPorCodigo(fabricante.getCodigo());
			// fabricanteEditar.setDescricao(fabricante.getDescricao());
			//
			// sessao.update(fabricanteEditar);

			// todos dados preenchidos forma de cima nao ja seta antes
			sessao.update(funcionario);

			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			ex.printStackTrace();

		} finally {
			sessao.close();
		}
	}
	
	public Funcionario autenticar(String cpf, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Funcionario funcionario = null;

		try {
			Query consulta = sessao.getNamedQuery("Funcionario.autenticar");
			consulta.setString("cpf", cpf);
			consulta.setString("senha", senha);
			
			funcionario = (Funcionario) consulta.uniqueResult();
			System.out.println(funcionario);
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcionario;
	}
}
