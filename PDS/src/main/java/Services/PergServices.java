package Services;

import java.util.*;

import dao.PerguntaDao;
import model.Pergunta;

public class PergServices {

	public static PerguntaDao perguntaDao; // criado pelo reinaldo -- não sei se faz sentido, mas deixa aí até resolvermos
	public static PerguntaDao perguntaDB = new PerguntaDao(); // banco com perguntas #SL
	public static Pergunta pergunta;
	public static long id;
	
	public static PerguntaDao getDao() {
		return perguntaDao;
	}
	
	public static void adicionar(Pergunta remote){
		perguntaDao.adicionar(remote); 	
	}
	
	public static void createPerguntaAdd(String perguntaText, String alt1, String alt2, String alt3, String alt4, int correta){
		pergunta.setPergunta(perguntaText);
		pergunta.setAlternativa1(alt1);
		pergunta.setAlternativa2(alt2);
		pergunta.setAlternativa3(alt3);
		pergunta.setAlternativa4(alt4);
		pergunta.setCorreta("" + correta); // corrigir a burrice do samuel
		pergunta.setIdUser(UserServices.getUserConnected().getId());
		
		perguntaDao.adicionar(pergunta);
		
	}

	public static Pergunta randomPerg() {
		
	    
		Random random = new Random();
	    id = random.nextInt(perguntaDB.getList().size()) + 1; // (max - min + 1) + min
	    System.out.println("TAMANHO "+perguntaDB.getList().size()+" id selecionado: "+id);
	    pergunta = perguntaDB.getPerguntaById(id);
	    
		return pergunta;
		//return null;
	}
}