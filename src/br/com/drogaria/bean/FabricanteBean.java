package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricanteCadastro;
	private List<Fabricante> listaFabricantes;
	private List<Fabricante> listaFabFiltrados;
	
	private String acao;
	
	public FabricanteBean() {
		if (FacesContext.getCurrentInstance().getExternalContext().getFlash() != null) {
			this.fabricanteCadastro = ((Fabricante) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("fabricante"));
			this.acao= (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("acao");
		}
		if (this.fabricanteCadastro == null) {
			this.fabricanteCadastro = new Fabricante();
			this.acao = "Novo";
		}

	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	public List<Fabricante> getListaFabFiltrados() {
		return listaFabFiltrados;
	}

	public void setListaFabFiltrados(List<Fabricante> listaFabFiltrados) {
		this.listaFabFiltrados = listaFabFiltrados;
	}

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}


	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);

			fabricanteCadastro = new Fabricante();

			FacesUtil.addMsgInfo("Fabricante salvo com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			//FacesUtil.addMsgError("Erro ao incluir fabricante: " + ex.getMessage());
		}

	}

	public void carregarPesquisa() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricantes = fabricanteDAO.listar();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao listar os fabricantes: " + ex.getMessage());
		}
	}

	public String carregarObjeto(Fabricante objeto, String acao) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getFlash().put("fabricante", objeto);
			externalContext.getFlash().put("acao", acao);
			externalContext.getFlash().setKeepMessages(true);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao carregar fabricante: " + ex.getMessage());
		}
		return "/pages/fabricanteCadastro.xhtml?faces-redirect=true";
	}
	
	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();
			
			FacesUtil.addMsgInfo("Fabricante excluido com Sucesso!");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao excluir fabricante: " + ex.getMessage());
		}
	}
	
	public void editar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);
			fabricanteCadastro = new Fabricante();
			
			FacesUtil.addMsgInfo("Fabricante editado com Sucesso!");

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgError("Erro ao editado fabricante: " + ex.getMessage());
		}
	}
}
