package bean; 


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import dao.DaoUsuario;
import entidades.PerfilUser;
import entidades.Pessoa;
import entidades.Sexo;
import repositorio.IDaoPessoa;
import repositorio.IDaoPessoaImpl;


@ViewScoped
@ManagedBean(name= "pessoaBean")	
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoUsuario<Pessoa> daoGeneric = new DaoUsuario<Pessoa>();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private IDaoPessoa iDaoPessoa = new IDaoPessoaImpl();

	
	
	
	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		pessoas.add(pessoa);
		carregarPessoas();
		return "index.xhtml";
	}
	public String cancelarPagina() {
		return "index.xhtml";
	}
	
	
	
	public String salvarPagina() {
		pessoa = daoGeneric.merge(pessoa);
		pessoas.add(pessoa);
		carregarPessoas();
		return "";
	}
	
	
	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	

	public String remove() {
	
		try {
			daoGeneric.removerUsuario(pessoa);
			pessoas.remove(pessoa);
			pessoa = new Pessoa();
			

		} catch (Exception e) {
				e.printStackTrace();
			}
		
			return "";
		
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}
	
	public String logar() {
		carregarPessoas();
		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		
		
		if(pessoaUser != null) {
			//Adicionar usuario a sesssao
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
			
			
			return "primeirapagina.jsf";
		
		}
		return "index.jsf";
	}
	
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		
		return pessoaUser.getPerfilUser().equals(acesso);
	}
	
	public String acessarCadastro() {
		return "cadastro.jsf";
	}
	
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}


	

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public PerfilUser[] getPerfil() {
		return PerfilUser.values();
	}
	
}
