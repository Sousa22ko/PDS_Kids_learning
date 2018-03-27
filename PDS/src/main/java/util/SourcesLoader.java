package util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SourcesLoader {
	
	
	public static void loadBackground(Pane background){
		
		background.setStyle("-fx-background-image: url(sources/Background.jpg);-fx-background-position: center center;" + 
	               "-fx-background-repeat: stretch; -fx-background-size : cover;");
	}
	
	public static void loadLogo(Pane background){
		
		background.setStyle("-fx-background-image: url(sources/logo.jpg);-fx-background-position: center center;" + 
	               "-fx-background-repeat: stretch; -fx-background-size : cover;");
	}
	
	
	public static void loadUserPhoto(Label foto, byte[] byt) throws IOException{
		
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(byt));
		Image imagem = SwingFXUtils.toFXImage(img, null );
		ImageView iv = new ImageView(imagem);
		foto.setGraphic(iv);
		foto.setPrefWidth(180);
		foto.setPrefHeight(180);
	}
	
	

}
