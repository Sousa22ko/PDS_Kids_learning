package util;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sources.ScreenConstants;

public class ScreenLibrary {
	
	private static AnchorPane pane;
	private static Stage primaryStage;
	private static Dimension dimension;

	
	public static void setPane(AnchorPane panenew){
		pane = panenew;
	}
	public static void setStage(Stage primaryStageNew){
		primaryStage = primaryStageNew;
	}
	public static void setDimension(Dimension dimensionNew){
		dimension = dimensionNew;
		dimension.height -= 35;
		dimension.width += 5;
	}
	public static AnchorPane getPane(){
		return pane;
	}
	public static Stage getStage(){
		return primaryStage;
	}
	public static Dimension getDimension(){
		return dimension;
	}
	
	public static void LoadTela(int id) throws UnsupportedEncodingException{
		ScreenBuilder.load_stage(pane, primaryStage);

		//-------------------------------------------------------------------------------------------//
		// presta atenção cambada!! o id é definido na classe sources/ScreenConstant				 //
		// aqui é so pra usar oque for definido la, n va bagunçar essa parada prfv  				 //
		// pra criar mais telas é só definir o id la, criar um if e criar um metodo no screenBuilder //
		//-------------------------------------------------------------------------------------------//
		
		//não esqueçam, todas as telas devem ter um controller
		//objetos da UI são criados no programa do scenebuilder e links entre objetos da classe controller 
		//e da interfaçe tbm são feitos por la
		
		
		if(id == ScreenConstants.IDLOGIN)
			ScreenBuilder.renderLoginScreen();// <-- crtl click na classe screenbuilder
		
		else if(id == ScreenConstants.IDHOME)
			ScreenBuilder.renderHomePage();
		
		else if(id == ScreenConstants.IDCADPERG)
			ScreenBuilder.renderCadastroPergunta();
		
		
		else throw new UnsupportedEncodingException("tela indefida");

		primaryStage.setWidth(dimension.width);
		primaryStage.setHeight(dimension.height);

	}
	
}
