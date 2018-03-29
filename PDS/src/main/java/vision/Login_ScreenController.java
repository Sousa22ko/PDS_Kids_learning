package vision;

import java.io.UnsupportedEncodingException;

//import org.apache.commons.validator.routines.EmailValidator;

import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import sources.ScreenConstants;
import util.LoggedUser;
import util.ScreenLibrary;
import util.SourcesLoader;



public class Login_ScreenController {

	@FXML
	private Button login;

	@FXML
	private Button cad;

	@FXML
	private TextField user;

	@FXML
	private PasswordField pass;

	@FXML
	private Pane logicPane;
	
	@FXML
	private Pane cadPane;

	@FXML
	private Pane background;

	@FXML
	private Pane logo;

	@FXML
	private AnchorPane masterPane;
	
	private User remote = new User();
	

	// private User remote = new User();

	@FXML
	public void initialize() {
		SourcesLoader.loadBackground(background);
		SourcesLoader.loadLogo(logo);

		// logicPane.setLayoutX((masterPane.getHeight() / 2) -
		// (logicPane.getHeight() / 2));
		logicPane.setLayoutX(533);
		// logicPane.setLayoutY((masterPane.getHeight() / 2) -
		// (logicPane.getHeight() / 2));
		logicPane.setLayoutY(174);
	}

	@FXML
	public void LoginHandler() {

		try {
			if (login()) {
				LoggedUser.setUserLogged(remote);
				ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
			}
			
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}
	
	public boolean login() {
		UserDao ud = new UserDao();
		User local, remote;

		local = new User();
		local.setNome(login.getText());
		local.setSenha(pass.getText());

		remote = ud.getUserByName(local.getNome());

		if (UserDao.comparePassword(local, remote)) {
			this.remote = remote;
			return true;
		} else {
			return false;
		}
	}

	@FXML
	public void CadastroHandler() {

		cadPane.setLayoutX(50);
		cadPane.setLayoutY(-275);
		cadPane.setPrefSize(700, 600);
		cadPane.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");
		cadPane.setVisible(true);
		
		TextField nomeT, emailT, userT, instT;
		DatePicker niver;
		PasswordField senhaT;
		ChoiceBox<String> tipo;
		Button cadastrar, cancelar;
		
		Label text, error;
		
		text = new Label("Cadastro de usuario");
		error = new Label();
		nomeT = new TextField();
		emailT = new TextField();
		userT = new TextField();
		instT = new TextField();
		niver = new DatePicker();
		senhaT = new PasswordField();
		
		cadastrar = new Button("cadastrar");
		cancelar = new Button("cancelar");
		
		
		nomeT.setPromptText("Nome*");
		emailT.setPromptText("Email*");
		userT.setPromptText("Usuario*");
		instT.setPromptText("Instituicao");
		niver.setPromptText("Nascimento");
		senhaT.setPromptText("Senha*");
		tipo = new ChoiceBox<String>(FXCollections.observableArrayList("Aluno", "Professor", "Sei la mano"));
		text.setFont(Font.font("arial", FontWeight.BOLD, 20));
		
		text.setLayoutX(250);
		text.setLayoutY(50);
		
		nomeT.setLayoutX(25);
		nomeT.setLayoutY(125);
		nomeT.setPrefSize(650, 10);
		
		emailT.setLayoutX(25);
		emailT.setLayoutY(165);
		emailT.setPrefSize(650, 10);
		
		userT.setLayoutX(25);
		userT.setLayoutY(205);
		userT.setPrefSize(650, 10);
		
		instT.setLayoutX(25);
		instT.setLayoutY(245);
		instT.setPrefSize(650, 10);
		
		niver.setLayoutX(25);
		niver.setLayoutY(285);
		niver.setPrefSize(650, 10);
		
		senhaT.setLayoutX(25);
		senhaT.setLayoutY(325);
		senhaT.setPrefSize(650, 10);
		
		tipo.setLayoutX(25);
		tipo.setLayoutY(365);
		tipo.setPrefSize(650, 10);
		
		cadastrar.setLayoutX(25);
		cadastrar.setLayoutY(405);
		cadastrar.setPrefSize(320, 10);
		
		cancelar.setLayoutX(355);
		cancelar.setLayoutY(405);
		cancelar.setPrefSize(320, 10);
		
		cadPane.getChildren().add(text);
		cadPane.getChildren().add(nomeT);
		cadPane.getChildren().add(emailT);
		cadPane.getChildren().add(userT);
		cadPane.getChildren().add(instT);
		cadPane.getChildren().add(niver);
		cadPane.getChildren().add(senhaT);
		cadPane.getChildren().add(tipo);
		cadPane.getChildren().add(cadastrar);
		cadPane.getChildren().add(cancelar);
		
				
		cadastrar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("cadastrado");
                
            }
        });
		
		cancelar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	cadPane.setVisible(false);
            	return;
            }
        });
			
	}
	
	/*public void createUser() {

		if (nome.getText() != null && nome.getText().length() < 30) {
			if (checkUni(nome.getText())) {
				novo.setNome(nome.getText());
				erru = false;
			} else {
				erru =true;
				error.setText("Usuario indisponivel");
			}
		} else {
			erru = true;
			error.setText("Usuario vazio ou muito grande (maximo 30 caracteres)");
		}

		if (EmailValidator.getInstance().isValid(email.getText())) {
			novo.setEmail(email.getText());
			erre = false;
		} else {
			erre = true;
			error.setText("Email invalido");
		}

		novo.setSenha(senha.getText());

		// novo.setLastAcess(new Date()); TODO get server time
		novo.setMaxStreak(0);
		novo.setLastLeague(0);

		if (erre == false && erru == false) {
			UserDao ud = new UserDao();
			ud.adicionar(novo);// persiste no bd
			SceneBuilder.loadLoginScreen();
		}
	}

	// checa se o usuario tem um login unico
	private static boolean checkUni(String nome) {
		UserDao ud = new UserDao();

		try {
			User check = ud.getUserByName(nome);
		} catch (NoResultException e) {
			return true;
		}
		return false;
	}
*/
}
