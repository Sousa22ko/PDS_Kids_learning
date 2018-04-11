package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "userName")
	private String userName;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "tipoUsuario")
	private int tipoUsuario;
	
	@Column(name = "instit")
	private String instit;
	
	public String getNome() {
		return nome;
	}
	
	public long getId(){
		return this.id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getInstit() {
		return instit;
	}

	public void setInstit(String instit) {
		this.instit = instit;
	}
	
	
}
