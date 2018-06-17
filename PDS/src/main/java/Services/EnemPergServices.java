package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.EnemPerguntaDao;
import model.EnemPergunta;
import util.LoggedUser;

public class EnemPergServices extends AbstractPergServices<EnemPergunta> {
	
	public EnemPergServices(){
		perguntaDao = new EnemPerguntaDao();
	}

	public void adicionar(EnemPergunta remote) throws Exception{
		
		if(remote.getCorreta() == null)
			throw new Exception("selecione a alternativa correta");
		else
			perguntaDao.adicionar(remote);
	}
	
	public EnemPergunta buscarPergunta(long x){
		return perguntaDao.buscar(x);
	}
	
	public EnemPergunta randonPergunta(){
		return perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
	}
	
	public List<EnemPergunta> listandoPerguntas(long idUser) {
		List<EnemPergunta> listTotal = new ArrayList<EnemPergunta>();
		List<EnemPergunta> listaPergs = new ArrayList<EnemPergunta>();
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

		List<EnemPergunta> listPerg = new ArrayList<EnemPergunta>();
		EnemPergunta nova;
		int correta;

		for (int i = perguntaDao.getList().size() + 1, j = 0; i < n + perguntaDao.getList().size() + 1
				|| j < 100; i++, j++) {

			correta = new Random().nextInt(5) + 1;

			nova = new EnemPergunta();

			nova.setPergunta("Pergunta ID" + i);

			if (correta == 1) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("A");
				nova.setCategoria("Fisica");

			} else if (correta == 2) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("B");
				nova.setCategoria("Fisica");

			} else if (correta == 3) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("C");
				nova.setCategoria("Fisica");

			} else if (correta == 4) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("D");
				nova.setCategoria("Fisica");

			} else if (correta == 5) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("E");
				nova.setCategoria("Fisica");

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
