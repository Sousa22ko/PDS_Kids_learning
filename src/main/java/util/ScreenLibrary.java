package util;

import java.awt.Dimension;
import java.io.UnsupportedEncodingException;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	
	public static void LoadTela(int id, int instance) throws UnsupportedEncodingException{
		ScreenBuilder.load_stage(pane, primaryStage);

		//-------------------------------------------------------------------------------------------//
		// presta aten��o cambada!! o id � definido na classe sources/ScreenConstant				 //
		// aqui � so pra usar oque for definido la, n va bagun�ar essa parada prfv  				 //
		// pra criar mais telas � s� definir o id la, criar um if e criar um metodo no screenBuilder //
		//-------------------------------------------------------------------------------------------//
		
		//n�o esque�am, todas as telas devem ter um controller
		//objetos da UI s�o criados no programa do scenebuilder e links entre objetos da classe controller 
		//e da interfa�e tbm s�o feitos por la
		
		
		if(instance == 0)
			SelectBuilder.load(id);
		else if(instance == 1)
			BuilderKids.load(id);
		else if(instance ==  2)
			BuilderEnem.load(id);
		else if(instance ==  3)
			BuilderMilhao.load(id);

		primaryStage.setWidth(dimension.width);
		primaryStage.setHeight(dimension.height);

	}
	
}
