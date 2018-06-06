package util;

import java.util.Observable;

//Fun fact: cronômetro em ingles é "cronometro"
public class RegressiveCronometer extends Timer {

	public RegressiveCronometer(Observable obs) {
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
			if (remainTime < 0.1d || vidas <= 0)
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

	public void update(Observable o, Object arg) {
		setChanged();
		if ((Boolean) arg) {
			acertos += 1;
			if (acertos < 10) {
				remainTime += 5;
				notifyObservers((String) ("" + 5));
			}
			if (acertos >= 10 && acertos <= 15) {
				remainTime += 2;
				notifyObservers((String) ("" + 2));
			}
			if (acertos >= 15) {
				remainTime += 1;
				notifyObservers((String) ("" + 1));
			}
		} else {
			remainTime -= 3;
			notifyObservers((String) ("" + (-3)));
		}

	}

}
