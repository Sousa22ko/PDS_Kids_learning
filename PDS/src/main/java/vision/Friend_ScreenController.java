package vision;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Services.SeguindoServices;
import Services.UserServices;
import dao.SeguindoDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Seguindo;
import model.User;
import sources.ScreenConstants;
import util.LoggedUser;
import util.ScreenLibrary;

public class Friend_ScreenController {

	@FXML
	private Pane pane;

	@FXML
	private Button bNext;

	@FXML
	private Button bPrev;

	private SeguindoDao seguindoDao = new SeguindoDao();
	private List<Seguindo> seguindo = new ArrayList<Seguindo>();
	
	private List<User> users = new ArrayList<User>();
	private int nPagina = 1;

	private boolean canNext = false;
	private boolean canPrev = false;

	private int listSize;
	private List<Button> buts = new ArrayList<Button>();
	private List<Pane> panesTuple = new ArrayList<Pane>();

	@FXML
	public void initialize() {
		users = UserServices.listUsers();
		listSize = users.size();
		System.out.println(listSize);
		loadPage();
	}

	private void loadPage() {

		canNext = false;
		canPrev = false;

		if (listSize == 0) {
			// ?? n�o tem nada pra fazer aqui
		} else {

			int maxSizedList;

			if (nPagina > 1) {
				canPrev = true;
				maxSizedList = (listSize - 1) - ((nPagina - 1) * 10);
				System.out.println(maxSizedList + " max ");
			}

			if (listSize > 10) {
				canNext = true;
			}
			if (listSize <= 10 && nPagina == 1) {
				maxSizedList = listSize;
			} else {
				maxSizedList = 10;
			}
			
			seguindo = seguindoDao.getList();

			for (int i = 0; i < maxSizedList; i++) { 
				final Button follow = new Button("seguir usu�rio");

				System.out.println(i + ((nPagina - 1) * 10));
				Label namePerson = new Label(users.get(i + ((nPagina - 1) * 10)).getNome() + " " + i);
				final int id = i;
				Pane tuple = new Pane();

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");
				
				for(int aux=0; aux<seguindo.size(); aux++){
					
					if(seguindo.get(aux).getIdSeguido() == users.get(i).getId() && LoggedUser.getLoggedUser().getId() == seguindo.get(aux).getIdUser()){
						System.out.println(LoggedUser.getLoggedUser().getNome() + " est� seguindo " + users.get(i).getId());
						follow.setDisable(true); //substituir por setVisible(false) para setar o outro bot�o como true
					}
				}

				tuple.getChildren().add(namePerson);
				tuple.getChildren().add(follow);

				namePerson.setLayoutX(50);
				follow.setLayoutX(550);

				namePerson.setLayoutY(15);
				follow.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);
				
				follow.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						System.out.println(users.get(id).getId() + " " + users.get(id).getNome()); //users.get(i).getId()
						if(SeguindoServices.seguir(LoggedUser.getLoggedUser().getId(), users.get(id).getId())){
							//chamar m�todo para ativar o bot�o de unfollow
							// e desativar o bot�o de follow passando o id para renderizar na posi��o certa
							follow.setDisable(true);
							System.out.println(LoggedUser.getLoggedUser().getNome() + " est� seguindo " + users.get(id).getId());
						}
					};
				});

				panesTuple.add(tuple);
				buts.add(follow);

			}
		}

		bNext.setDisable(!canNext);
		bPrev.setDisable(!canPrev);

		loadTuplesOnScreen();

	}

	private void loadTuplesOnScreen() {

		for (int i = 0; i < panesTuple.size(); i++)
			pane.getChildren().add(panesTuple.get(i));
	}

	private void unloadTuplesOnScreen() {

		for (int i = 0; i < panesTuple.size(); i++)
			pane.getChildren().remove(panesTuple.get(i));
		panesTuple.clear();
	}

	@FXML
	private void handlerVoltar() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handlerNextPage() {
		unloadTuplesOnScreen();
		nPagina += 1;
		loadPage();
	}

	@FXML
	private void handlerPrevPage() {
		unloadTuplesOnScreen();
		nPagina -= 1;
		loadPage();
	}

}
