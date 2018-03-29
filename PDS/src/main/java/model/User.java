package model;

import javafx.scene.chart.PieChart.Data;
import javax.persistence.Entity;


@Entity
public class User {

	private String nome;
	
	private String userName;

	private String senha;
	
	private Data nascimento;
	
	private String email;
	
	private int tipoUsuario;
	
	private String instit;
	
	public String getNome() {
		return nome;
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

	public Data getNascimento() {
		return nascimento;
	}

	public void setNascimento(Data nascimento) {
		this.nascimento = nascimento;
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
