package Services;

import dao.SeguindoDao;
import model.Seguindo;

public class SeguindoServices {

	public static SeguindoDao seguindoDao = new SeguindoDao();
	public static Seguindo seguindo = new Seguindo();
	
	//user1 -> id do usuário quem segue
	//user2 -> id do usuário de quem está sendo seguido
	public static boolean seguir(long user1, long user2){
		seguindo.setIdSeguido(user2);
		seguindo.setIdUser(user1);
		seguindoDao.adicionar(seguindo);
		return false;
	}
}