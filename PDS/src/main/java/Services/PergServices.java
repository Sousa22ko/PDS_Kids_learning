package Services;

import dao.PerguntaDao;
import model.Pergunta;

public class PergServices {

	public static PerguntaDao perguntaDao;
	public static Pergunta pergunta;
	
	
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
}