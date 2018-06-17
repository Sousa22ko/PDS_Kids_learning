package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.MilhaoPerguntaDao;
import model.MilhaoPergunta;
import util.LoggedUser;

public class MilhaoPergServices extends AbstractPergServices <MilhaoPergunta> {
	
	public MilhaoPergServices(){
		perguntaDao = new MilhaoPerguntaDao();
	}
	
	public MilhaoPerguntaDao getDao(){
		return (MilhaoPerguntaDao)perguntaDao;
	}

	public void adicionar(MilhaoPergunta remote) throws Exception{
		
		if(remote.getCorreta() == null)
			throw new Exception("selecione a alternativa correta");
		else
			perguntaDao.adicionar(remote);
	}
	
	public List<MilhaoPergunta> listandoPerguntas(long idUser) {
		List<MilhaoPergunta> listTotal = new ArrayList<MilhaoPergunta>();
		List<MilhaoPergunta> listaPergs = new ArrayList<MilhaoPergunta>();
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

	public void populandoPergunta(int n) {

		List<MilhaoPergunta> listPerg = new ArrayList<MilhaoPergunta>();
		MilhaoPergunta nova;
		int correta;

		for (int i = perguntaDao.getList().size() + 1, j = 0; i < n + perguntaDao.getList().size() + 1
				|| j < 100; i++, j++) {

			correta = new Random().nextInt(4) + 1;

			nova = new MilhaoPergunta();

			nova.setPergunta("Pergunta ID" + i);

			if (correta == 1) {
				nova.setAlternativa1("CORRETA");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa1");
				nova.setDica("A resposta está entre a 1 e a 2");

			} else if (correta == 2) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("CORRETA");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa2");
				nova.setDica("A resposta está entre a 1 e a 2");

			} else if (correta == 3) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("CORRETA");
				nova.setAlternativa4("Alternativa 4");

				nova.setCorreta("alternativa3");
				nova.setDica("A resposta está entre a 3 e a 4");

			} else if (correta == 4) {
				nova.setAlternativa1("Alternativa 1");
				nova.setAlternativa2("Alternativa 2");
				nova.setAlternativa3("Alternativa 3");
				nova.setAlternativa4("CORRETA");

				nova.setCorreta("alternativa4");
				nova.setDica("A resposta está entre a 3 e a 4");

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
