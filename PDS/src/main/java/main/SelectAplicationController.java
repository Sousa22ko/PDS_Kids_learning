package main;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import sources.ScreenConstants;
import util.SceneBuilder;
import util.SharedInfo;

public class SelectAplicationController {

	@FXML
	public void handlerKids(){
		SharedInfo.setInstance(1); //1Kid 2Enem 3Milhao
		try {
			SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN , SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// <-- vão dando crtl click no metodo pra ver como funciona
	}
	
	@FXML
	public void handlerEnem(){
		SharedInfo.setInstance(2); //1Kid 2Enem 3Milhao
		try {
			SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN , SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// <-- vão dando crtl click no metodo pra ver como funciona
	}
	
	@FXML
	public void handlerMilhao(){
		SharedInfo.setInstance(3); //1Kid 2Enem 3Milhao
		try {
			SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN , SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// <-- vão dando crtl click no metodo pra ver como funciona
	}
	
	
}
