package Services;

import java.util.*;

import dao.PerguntaDao;
import model.Pergunta;
import util.LoggedUser;

public class PergServices {

	public static PerguntaDao perguntaDao = new PerguntaDao(); // banco com perguntas #SL
	public static Pergunta pergunta;
	public static long id;
	
	public static PerguntaDao getDao() {
		return perguntaDao;
	}
	
	public static void adicionar(Pergunta remote){
		perguntaDao.adicionar(remote); 	
	}
	
	/*public static void createPerguntaAdd(String perguntaText, String alt1, String alt2, String alt3, String alt4, int correta){
		pergunta.setPergunta(perguntaText);
		pergunta.setAlternativa1(alt1);
		pergunta.setAlternativa2(alt2);
		pergunta.setAlternativa3(alt3);
		pergunta.setAlternativa4(alt4);
		pergunta.setCorreta("" + correta); // corrigir a burrice do samuel
		pergunta.setIdUser(UserServices.getUserConnected().getId());
		
		perguntaDao.adicionar(pergunta);
		
	}*/

	public static Pergunta randomPerg() {
		
		return perguntaDao.getPerguntaById(new Random().nextInt(perguntaDao.getList().size()-1)+1);
	}
	
	public static void populandoPergunta(int n){
		
		List<Pergunta> listPerg = new ArrayList<Pergunta>();
		Pergunta nova;
		int correta;
		
		for(int i=perguntaDao.getList().size()+1, j=0; i< n+perguntaDao.getList().size()+1 || j<100;i++, j++){
			//createPerguntaAdd("Pergunta ID"+i, "Alternativa 1", "Alternativa 2", "Alternativa 3", "Alternativa 4", new Random().nextInt(4)+1);
			
			correta = new Random().nextInt(4)+1;
			
			nova = new Pergunta();
			
			nova.setPergunta("Pergunta ID"+i);
			nova.setAlternativa1("Alternativa 1");
			nova.setAlternativa2("Alternativa 2");
			nova.setAlternativa3("Alternativa 3");
			nova.setAlternativa4("Alternativa 4");
			
			if(correta == 1){
				nova.setCorreta("alternativa1");
			} else if(correta == 2){
				nova.setCorreta("alternativa2");
			} else if(correta == 3){
				nova.setCorreta("alternativa3");
			} else if(correta == 4){
				nova.setCorreta("alternativa4");
			} else
				System.out.println("DEU ERRO NEGADA");
			
			nova.setIdUser(LoggedUser.getLoggedUser().getId());
			listPerg.add(nova); //adicionando a pergunta na lista de perguntas
			perguntaDao.adicionar(listPerg.get(j)); //inserindo a pergunta[j] no banco de dados
			
			if(j<100)
				nova = null;
			else
				break;
			
			System.out.println("Tamanho do array: "+listPerg.size());
			System.out.println("valor de I: "+i);
			System.out.println("Valor de J: "+j);
		}
	}
}