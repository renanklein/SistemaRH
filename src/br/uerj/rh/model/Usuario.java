package br.uerj.rh.model;

public class Usuario {
	private String usuario, senha/*, nome_usuario*/;
	private int permissao;
	public Usuario(String usuario, String senha, /*String nome_usuario,*/ int permissao) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		//this.nome_usuario = nome_usuario;
		this.permissao = permissao;
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/*public String getNomeUsuario() {
		return nome_usuario;
	}
	
	public void setNomeUsuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}*/
	
	public int getPermissao() {
		return permissao;
	}
	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}
}
