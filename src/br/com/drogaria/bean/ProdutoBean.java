package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean {
	private Produto produtoCadastro;
	private List<Produto> listaProdutos;
	private List<Produto> listaProdFiltrados;

	private String acao;

	private List<Fabricante> listaFabricantes;

	public List<Fabricante> getListaFabricantes() {
		if (listaFabricantes == null) {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
		}
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public ProdutoBean() {
		if (FacesContext.getCurrentInstance().getExternalContext().getFlash() != null) {
			this.produtoCadastro = ((Produto) FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.get("produto"));
			this.acao = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("acao");
		}
		if (this.produtoCadastro == null) {
			this.produtoCadastro = new Produto();
			this.acao = "Novo";
		}
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Produto getProdutoCadastro() {
		if (produtoCadastro == null) {
			produtoCadastro = new Produto();
		}
		return produtoCadastro;
	}

	public List<Produto> getListaProdFiltrados() {
		return listaProdFiltrados;
	}

	public void setProdutoCadastro(Produto produtoCadastro) {
		this.produtoCadastro = produtoCadastro;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public void setListaProdFiltrados(List<Produto> listaProdFiltrados) {
		this.listaProdFiltrados = listaProdFiltrados;
	}

	public void novo() {
		produtoCadastro = new Produto();
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produtoCadastro);

			produtoCadastro = new Produto();

			FacesUtil.addMsgInfo("Produto salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao incluir Produto: " + ex.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao listar os produtos: " + ex.getMessage());
		}
	}

	public String carregarObjeto(Produto objeto, String acao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getFlash().put("produto", objeto);
			externalContext.getFlash().put("acao", acao);
			externalContext.getFlash().setKeepMessages(true);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao carregar Produto: " + ex.getMessage());
		}
		return "/pages/produtoCadastro.xhtml?faces-redirect=true";
	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produtoCadastro);
			produtoCadastro = new Produto();

			FacesUtil.addMsgInfo("Produto excluido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao excluir Produto: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produtoCadastro);
			produtoCadastro = new Produto();

			FacesUtil.addMsgInfo("Produto editado com Sucesso!");

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao editar Produto: " + ex.getMessage());
		}
	}
}
