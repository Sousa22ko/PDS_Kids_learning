package util;

import java.util.ArrayList;
import java.util.Observable;

import antlr.collections.List;

//Fun fact: cronômetro em ingles é "cronometro"
public class MilhaoRegressiveCronometer extends Timer {
	
	private int qtdPergs = 24; // variável responsável por parar a geração de perguntas

	public MilhaoRegressiveCronometer(Observable obs) {
		super(obs);
		remainTime = 45.0d;
	}

	public void reloadRelogio() {
		remainTime = 45.0d;
		acertos = 0;
		erros = 0;
		vidas = 3;
	}

	public void run() {
		while (true) {
			setChanged();
			if (remainTime < 0.1d || qtdPergs <= 0)
				notifyObservers((Boolean) false);
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remainTime -= 0.1000000000d;
				notifyObservers((Double) remainTime);
			}
		}
	}

	public void pontuacao(boolean arg) {
		// potuação não utilizada para esse modo de jogo
	}

	public void update(Observable o, Object arg) {
		setChanged();
		if ((Boolean) arg) {
			acertos += 1;
			remainTime = 45.0;
			qtdPergs--;
			if(qtdPergs == 0) {
				notifyObservers((String) "CAMPEAO");
			}
			//
		} else {
			remainTime = 0.0;
			notifyObservers((Boolean) false);
		}

	}

}
