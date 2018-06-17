package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MilhaoPergunta extends HPergunta {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	@Column(name = "dica")
	private String dica;

	@Column(name = "alternativa1")
	private String alternativa1;
	
	@Column(name = "alternativa2")
	private String alternativa2;
	
	@Column(name = "alternativa3")
	private String alternativa3;
	
	@Column(name = "alternativa4")
	private String alternativa4;
	
	@Column(name = "alternativa_correta")
	private String correta;
	
	@Column(name = "id_user")
	private long idUser;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}
	
	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public String getAlternativa1() {
		return alternativa1;
	}

	public void setAlternativa1(String alternativa1) {
		this.alternativa1 = alternativa1;
	}

	public String getAlternativa2() {
		return alternativa2;
	}

	public void setAlternativa2(String alternativa2) {
		this.alternativa2 = alternativa2;
	}

	public String getAlternativa3() {
		return alternativa3;
	}

	public void setAlternativa3(String alternativa3) {
		this.alternativa3 = alternativa3;
	}

	public String getAlternativa4() {
		return alternativa4;
	}

	public void setAlternativa4(String alternativa4) {
		this.alternativa4 = alternativa4;
	}

	public String getCorreta() {
		return correta;
	}

	public void setCorreta(String correta) {
		this.correta = correta;
	}

	public long getId() {
		return id;
	}
	
	

}