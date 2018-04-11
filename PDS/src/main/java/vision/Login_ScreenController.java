package vision;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.persistence.NoResultException;

import org.apache.commons.validator.routines.EmailValidator;

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
	
	@FXML
	private Label error;

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
			error.setText("Usuario não econtrado");
			e.printStackTrace();
		}

	}

	public boolean login() {
		UserDao ud = new UserDao();
		User local, remote;

		local = new User();
		local.setUserName(user.getText());
		local.setSenha(pass.getText());
		
		remote = ud.getUserByUserName(local.getUserName());
		
		System.out.println(remote.getSenha() + " r l " + local.getSenha());

		if (UserDao.comparePassword(local, remote)) {
			this.remote = remote;
			return true;
		} else {
			return false;
		}
	}

	@FXML
	public void CadastroHandler() throws ParseException {

		cadPane.setLayoutX(50);
		cadPane.setLayoutY(-275);
		cadPane.setPrefSize(700, 600);
		cadPane.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");
		cadPane.setVisible(true);

		final TextField nomeT;
		final TextField emailT;
		final TextField userT;
		final TextField instT;
		final DatePicker niver;
		final PasswordField senhaT;
		final ChoiceBox<String> tipo;
		Button cadastrar, cancelar;

		Label text;

		text = new Label("Cadastro de usuario");
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
		tipo = new ChoiceBox<String>(FXCollections.observableArrayList("Aluno", "Professor", "admin"));
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
		
		error.setLayoutX(100);
		error.setLayoutY(600);

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
				remote = createUser(nomeT.getText(), emailT.getText(), userT.getText(), instT.getText(),
						senhaT.getText(),tipo.getValue(), error);
				
				System.out.println(remote + " remote");
				UserDao ud = new UserDao();
				ud.adicionar(remote);
				cadPane.setVisible(false);
			}
		});

		cancelar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				cadPane.setVisible(false);
				return;
			}
		});
	}

	public User createUser(String nome, String email, String user, String inst, String senha, String tipo, Label error) {

		User novo = new User();
		Boolean erru, erre;

		if (nome != null && nome.length() < 30) {
			if (checkUni(user)) {
				novo.setUserName(user);
				erru = false;
			} else {
				erru = true;
				error.setText("Usuario indisponivel");
			}
		} else {
			erru = true;
			error.setText("Usuario vazio ou muito grande (maximo 30 caracteres)");
		}

		if (EmailValidator.getInstance().isValid(email)) {
			novo.setEmail(email);
			erre = false;
		} else {
			erre = true;
			error.setText("Email invalido");
		}
		
		System.out.println(erre + " " + erru);
		if (erre == false && erru == false) {
			
			novo.setNome(nome);
			novo.setSenha(senha);
			novo.setInstit(inst);
			
			novo.setTipoUsuario(3);// TOFIX --------------------------
			
			
			UserDao ud = new UserDao();
			ud.adicionar(novo);// persiste no bd
			return novo;
		} else
			return null;
	}

	// checa se o usuario tem um login unico
	private static boolean checkUni(String userName) {
		UserDao ud1 = new UserDao();

		try {
			User check = ud1.getUserByUserName(userName);
			System.out.println(check.getUserName() + " do banco");
		} catch (NoResultException e) {
			return true;
		}
		return false;
	}

}
