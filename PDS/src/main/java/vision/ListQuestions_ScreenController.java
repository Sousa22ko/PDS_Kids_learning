package vision;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Services.PergServices;
import Services.SeguindoServices;
import Services.UserServices;
import dao.SeguindoDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Pergunta;
import model.Seguindo;
import model.User;
import sources.ScreenConstants;
import util.LoggedUser;
import util.ScreenLibrary;

public class ListQuestions_ScreenController {

	@FXML
	private Pane pane;

	@FXML
	private Button bNext;

	@FXML
	private Button bPrev;

	private SeguindoDao seguindoDao = new SeguindoDao();
	private List<Seguindo> seguindo = new ArrayList<Seguindo>();
	
	private List<Pergunta> pergunta = new ArrayList<Pergunta>();
	private int nPagina = 1;

	private boolean canNext = false;
	private boolean canPrev = false;

	private int listSize;
	private List<Button> buts = new ArrayList<Button>();
	private List<Pane> panesTuple = new ArrayList<Pane>();

	@FXML
	public void initialize() {
		pergunta = PergServices.listandoPerguntas(LoggedUser.getLoggedUser().getId());
		listSize = pergunta.size();
		System.out.println(listSize);
		loadPage();
	}

	private void loadPage() {

		canNext = false;
		canPrev = false;

		if (listSize == 0) {
			// ?? não tem nada pra fazer aqui
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
				final Button editar = new Button("Editar");

				System.out.println(i + ((nPagina - 1) * 10));
				Label namePergunta = new Label(pergunta.get(i + ((nPagina - 1) * 10)).getPergunta());
				final int id = i + ((nPagina - 1) * 10);
				Pane tuple = new Pane();

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");

				tuple.getChildren().add(namePergunta);
				tuple.getChildren().add(editar);

				namePergunta.setLayoutX(50);
				editar.setLayoutX(550);

				namePergunta.setLayoutY(15);
				editar.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);
				
				editar.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						//if(SeguindoServices.seguir(LoggedUser.getLoggedUser().getId(), pergunta.get(id).getId())){

							//editar.setVisible(false);recebeIdPergunta(pergunta.get(id).getId())
							System.out.println(LoggedUser.getLoggedUser().getNome() + " está editando a pergunta " + pergunta.get(id).getId());
						//}
					};
				});

				panesTuple.add(tuple);
				buts.add(editar);
				

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
