package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Pergunta;
import util.LoggedUser;

public class PergServices extends AbstractPergServices {

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
