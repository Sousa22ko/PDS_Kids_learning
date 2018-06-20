package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.KidsPerguntaDao;
import model.Pergunta;
import util.LoggedUser;

public class KidPergServices extends AbstractPergServices <Pergunta> {
	
	public KidPergServices(){
		perguntaDao = new KidsPerguntaDao();
	}

	public void adicionar(Pergunta remote) throws Exception{
		
		if(remote.getCorreta() == "Alternativa 1"){
			remote.setCorreta("alternativa1");
		} else if(remote.getCorreta() == "Alternativa 2"){
			remote.setCorreta("alternativa2");
		} else if(remote.getCorreta() == "Alternativa 3"){
			remote.setCorreta("alternativa3");
		} else if(remote.getCorreta() == "Alternativa 4"){
			remote.setCorreta("alternativa4");
		} else
			throw new Exception("erro da variavel", null);
		
		perguntaDao.adicionar(remote);
	}
	
	public List<Pergunta> listandoPerguntas(long idUser) {
		List<Pergunta> listTotal = new ArrayList<Pergunta>();
		List<Pergunta> listaPergs = new ArrayList<Pergunta>();
		listTotal = perguntaDao.getList();
		for (int i = 0; i < listTotal.size(); i++) {
			if (listTotal.get(i).getIdUser() == idUser) {
				listaPergs.add(listTotal.get(i)); // inserindo na lista o ID da
													// pergunta cadastrada pelo
													// usuário idUser
			}
		}
		return listaPergs;
	}
	
<<<<<<< HEAD:PDS/src/main/java/Services/PergServices.java
	public Pergunta randomPerg() {	
		return perguntaDao.getPerguntaById(new Random().nextInt(perguntaDao.getList().size()-1)+1);
=======
	public Pergunta buscar(long x){
		return perguntaDao.buscar(x);
	}
	
	public Pergunta randomPergunta(){
		return perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
>>>>>>> BranchRefatoracao:PDS/src/main/java/Services/KidPergServices.java
	}

	public void populandoPergunta(int n) {

		List<Pergunta> listPerg = new ArrayList<Pergunta>();
		Pergunta nova;
		int correta;

		for (int i = perguntaDao.getList().size() + 1, j = 0; i < n + perguntaDao.getList().size() + 1
				|| j < 100; i++, j++) {

			correta = new Random().nextInt(4) + 1;

			nova = new Pergunta();

			nova.setPergunta("Pergunta ID" + i);

			if (correta == 1) {
				nova.setAlternativa1("CORRETA");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa1");

			} else if (correta == 2) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("CORRETA");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa2");

			} else if (correta == 3) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("CORRETA");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa3");

			} else if (correta == 4) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("CORRETA");

				nova.setCorreta("alternativa4");

			} else
				System.out.println("DEU ERRO NEGADA");

			nova.setIdUser(LoggedUser.getLoggedUser().getId());
			listPerg.add(nova); // adicionando a pergunta na lista de perguntas
			perguntaDao.adicionar(listPerg.get(j)); // inserindo a pergunta[j]
													// no banco de dados

			if (j < 100)
				nova = null;
			else
				break;

			System.out.println("Tamanho do array: " + listPerg.size());
			System.out.println("valor de I: " + i);
			System.out.println("Valor de J: " + j);
		}
	}
}
