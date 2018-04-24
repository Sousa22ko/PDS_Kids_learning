package vision;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Services.UserServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.User;
import sources.ScreenConstants;
import util.ScreenLibrary;

public class Friend_ScreenController {

	@FXML
	private Pane pane;

	@FXML
	private Button bNext;

	@FXML
	private Button bPrev;

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

			for (int i = 0; i < maxSizedList; i++) {
				Button follow = new Button("seguir usuário");

				System.out.println(i + ((nPagina - 1) * 10));
				final Label namePerson = new Label(users.get(i + ((nPagina - 1) * 10)).getNome() + " " + i);
				Pane tuple = new Pane();

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");

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
						System.out.println(namePerson.getText());
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
