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
	private List<User> local = new ArrayList<User>();

	private boolean canNext = false;
	private boolean canPrev = false;

	private List<Button> buts = new ArrayList<Button>();

	@FXML
	public void initialize() {
		loadPage();
	}

	private void loadPage() {
		
		
		users = UserServices.listUsers();
		int listSize = users.size();

		System.out.println(users.size());
		
		if (listSize == 0) {

		} else {
			
			int maxSizedList;
			
			
			if(nPagina > 1){
				canPrev = true;
				maxSizedList = listSize - ((nPagina - 1) * 10);
			}			
			
			if (listSize < 10 && nPagina == 1){
				maxSizedList = listSize;
			}
			else{
				maxSizedList = 10;
				canNext = true;
			}

			for (int i = 0; i < maxSizedList - 1; i++) {
				Button follow = new Button("seguir usuário");
				final Label namePerson = new Label(users.get(i + ((nPagina - 1) * 10)).getNome());

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

				pane.getChildren().add(tuple);
				buts.add(follow);

			}
		}
		
		bNext.setDisable(!canNext);
		bPrev.setDisable(!canPrev);

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
		nPagina -=1;
		loadPage();
	}

	@FXML
	private void handlerPrevPage() {
		nPagina +=1;
		loadPage();
	}

}
