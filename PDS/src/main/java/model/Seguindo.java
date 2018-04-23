package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Seguindo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "idUser") //id do usuário seguidor
	private long idUser;
	
	@Column(name = "idSeguido") // id do usuário que está sendo seguido pelo seguidor
	private long idSeguido;
	
	public long getId(){
		return this.id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdSeguido() {
		return idSeguido;
	}

	public void setIdSeguido(long idSeguido) {
		this.idSeguido = idSeguido;
	}
	
}
