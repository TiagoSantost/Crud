package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;


import entidades.Pessoa;
import entidades.TelefoneUser;
import repositorio.IDaoTelefone;
import repositorio.IDaoTelefoneImpl;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean {
	
	private TelefoneUser telefone = new TelefoneUser(); 
	private DaoGeneric<TelefoneUser> daoGeneric = new DaoGeneric<TelefoneUser>();
	private List<TelefoneUser> telefones = new ArrayList<TelefoneUser>();
	private IDaoTelefone daoTelefone = new IDaoTelefoneImpl();	
	
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		
		telefone.setUsuarioPessoa(pessoaUser);
		telefone = daoGeneric.merge(telefone);
		
		carregarTelefones();

		return "";
	}
	
	@PostConstruct
	private void carregarTelefones() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		telefones = daoTelefone.consultarTelefone(pessoaUser.getId());
	}
	
	public String novo() {
		telefone = new TelefoneUser();
		return "";
	}
	
	public String remover() {
		daoGeneric.deletePorId(telefone);
		telefone = new TelefoneUser();
		carregarTelefones();
		return"";
	}

	public TelefoneUser getTelefone() {
		return telefone;
	}

	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}

	public DaoGeneric<TelefoneUser> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<TelefoneUser> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public List<TelefoneUser> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneUser> telefones) {
		this.telefones = telefones;
	}

	public IDaoTelefone getDaoTelefone() {
		return daoTelefone;
	}

	public void setDaoTelefone(IDaoTelefone daoTelefone) {
		this.daoTelefone = daoTelefone;
	}
	
}
