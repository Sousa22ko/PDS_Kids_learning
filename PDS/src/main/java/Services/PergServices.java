package Services;

import dao.PerguntaDao;
import model.Pergunta;

public class PergServices {

	public static PerguntaDao perguntaDao;
	public static Pergunta pergunta;
	
	
	public static PerguntaDao getDao() {
		return perguntaDao;
	}
	
}