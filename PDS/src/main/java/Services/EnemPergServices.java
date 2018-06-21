package Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.EnemPerguntaDao;
import model.EnemPergunta;
import util.LoggedUser;

public class EnemPergServices extends AbstractPergServices<EnemPergunta> {
	public List<String> materias = new ArrayList<String>();
	public List<Integer> qtdPergMaterias = new ArrayList<Integer>();
	public List<EnemPergunta> listPerguntas = new ArrayList<EnemPergunta>();
	
	public EnemPergServices(){
		materias.add("Fisica");
		qtdPergMaterias.add(15);
		
		materias.add("Biologia");
		qtdPergMaterias.add(15);
		
		materias.add("Ingles");
		qtdPergMaterias.add(10);
		
		materias.add("Espanhol");
		qtdPergMaterias.add(10);
		
		materias.add("Portugues");
		qtdPergMaterias.add(20);
		
		materias.add("Matematica");
		qtdPergMaterias.add(20);
		
		materias.add("Quimica");
		qtdPergMaterias.add(15);
		
		materias.add("Historia");
		qtdPergMaterias.add(15);
		
		materias.add("Geografia");
		qtdPergMaterias.add(15);
		
		materias.add("Filosofia");
		qtdPergMaterias.add(15);
		
		materias.add("Sociologia");
		qtdPergMaterias.add(15);
		
		materias.add("Artes");
		qtdPergMaterias.add(15);
		
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
	
	public EnemPergunta getPergunta(int index) {
		return listPerguntas.get(index);
	}
	
	public void imprimePerguntas() {
		for(int i=0; i<180; i++) {
			System.out.println("Valor de i: "+i +"\nId da pergunta: "+listPerguntas.get(i));
		}
	}
	
	public void randonPergunta(){
		//int x = new Random().nextInt(11); //aaa
		//String escolhida = materias.get(x);
		EnemPergunta pergunta = perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
		
		for(int i=0; i<materias.size(); i++) {
			for(int j=0; j<qtdPergMaterias.get(i);) {
				if(pergunta.getCategoria().equals(materias.get(i))) {
					System.out.println("inserindo pergunta "+i+" na materia "+pergunta.getCategoria());
					listPerguntas.add(pergunta);
					j++;
				}
				pergunta = perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
			}
		}
		
		/*while(true){
			EnemPergunta p = perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
			if(p.getCategoria().equals(escolhida) && qtdPergMaterias.get(x) > 0) {
				qtdPergMaterias.set(x, qtdPergMaterias.get(x)-1);
				return p;
			}
		}*/
		//return null;
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
				nova.setCategoria("Quimica");

			} else if (correta == 2) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("B");
				nova.setCategoria("Artes");

			} else if (correta == 3) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("C");
				nova.setCategoria("Artes");

			} else if (correta == 4) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("D");
				nova.setCategoria("Artes");

			} else if (correta == 5) {
				nova.setAlternativa1("A");
				nova.setAlternativa2("B");
				nova.setAlternativa3("C");
				nova.setAlternativa4("D");
				nova.setAlternativa5("E");
				
				nova.setCorreta("E");
				nova.setCategoria("Artes");

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

	@Override
	public EnemPergunta absRandomPerg() {
		return perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
		}

}
