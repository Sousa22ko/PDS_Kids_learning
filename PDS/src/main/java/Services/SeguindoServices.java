package Services;

import dao.SeguindoDao;
import model.Seguindo;

public class SeguindoServices {

	public static SeguindoDao seguindoDao = new SeguindoDao();
	public static Seguindo seguindo = new Seguindo();
	
	//user1 -> id do usuário quem segue
	//user2 -> id do usuário de quem está sendo seguido
	public static boolean seguir(long user1, long user2){
		System.out.println("User"+user1+" está seguindo User"+user2);
		
		seguindo = new Seguindo();
		seguindo.setIdUser(user1);
		seguindo.setIdSeguido(user2);
		
		seguindoDao.adicionar(seguindo);
		return true;
	}
	
	public static boolean unfollow(long user1, long user2){
		//System.out.println("User"+user1+" está deixando de seguir User"+user2);
		for(int i=0; i<seguindoDao.getList().size(); i++){
			if(seguindoDao.getList().get(i).getIdUser() == user1 && seguindoDao.getList().get(i).getIdSeguido() == user2){
				System.out.println("DENTRO DO IF: User"+user1+" está deixando de seguir User"+user2);
				seguindoDao.remover(seguindoDao.getList().get(i).getId());
				return true;
			}
		}
		//seguindo.setIdSeguido(user2);
		//seguindo.setIdUser(user1);
		//seguindoDao.remover(user1);
		return false;
	}
}