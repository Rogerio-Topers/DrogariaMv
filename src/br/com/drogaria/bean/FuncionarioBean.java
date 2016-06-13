package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFunFiltrados;
	
	private String acao;
	
	public FuncionarioBean(){
		if (FacesContext.getCurrentInstance().getExternalContext().getFlash() != null) {
			this.funcionarioCadastro = ((Funcionario) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("funcionario"));
			this.acao= (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("acao");
		}
		if (this.funcionarioCadastro == null) {
			this.funcionarioCadastro = new Funcionario();
			this.acao = "Novo";
		}
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFunFiltrados() {
		return listaFunFiltrados;
	}

	public void setListaFunFiltrados(List<Funcionario> listaFunFiltrados) {
		this.listaFunFiltrados = listaFunFiltrados;
	}

	public Funcionario getFuncionarioCadastro() {
		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public void novo(){
		funcionarioCadastro = new Funcionario();
	}
	
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.salvar(funcionarioCadastro);
			
			funcionarioCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Funcionário salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao incluir funcionário: " + ex.getMessage());
		}
	}
	
	public void carregarPesquisa() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao listar os funcionários: " + ex.getMessage());
		}
	}

	public String carregarObjeto(Funcionario objeto, String acao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getFlash().put("funcionario", objeto);
			externalContext.getFlash().put("acao", acao);
			externalContext.getFlash().setKeepMessages(true);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao carregar funcionário: " + ex.getMessage());
		}
		return "/pages/funcionarioCadastro.xhtml?faces-redirect=true";
	}
	
	public void excluir() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionarioCadastro);
			funcionarioCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Funcionário excluido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao excluir funcionário: " + ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			funcionarioDAO.editar(funcionarioCadastro);
			
			funcionarioCadastro = new Funcionario();
			
			FacesUtil.addMsgInfo("Funcionário editado com Sucesso!");

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao editar funcionário: " + ex.getMessage());
		}
	}
}
