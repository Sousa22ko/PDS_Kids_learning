package util;

import java.util.Observable;

public class ProgressiveCronometer extends Timer {

	public ProgressiveCronometer(Observable obs) {
		super(obs);
		remainTime = 0.2d;
	}

	public void reloadRelogio() {
		remainTime = 0.2d;
		acertos = 0;
		erros = 0;
		vidas = 3;
	}

	public void run() {
		while (true) {
			setChanged();
			if (remainTime < 0.1d || vidas == 0)
				notifyObservers((Boolean) false);
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remainTime += 0.1000000000d;
				notifyObservers((Double) remainTime);
			}
		}
	}

	public void pontuacao(boolean arg) {
		if (arg) {
			if (acertos <= 5)
				if (remainTime <= 20)
					pontuacao += (1000 - (25 * remainTime) + (75 * acertos));
				else {
					if (remainTime <= 100)
						pontuacao += 200 + (acertos * 75) - remainTime;
					else
						pontuacao += 200 + (acertos * 75) - 100;
				}
			else if (remainTime <= 20)
				pontuacao += (1000 - ((25) * remainTime) + 375);
			else {
				if (remainTime <= 100)
					pontuacao += 200 + 375 - remainTime;
				else
					pontuacao += 200 + 375 - 100;
			}
		} else {
			if (pontuacao >= (75 + 2 * remainTime))
				pontuacao -= (75 + 2 * remainTime);
			else
				pontuacao = 0;
		}
	}

	public void update(Observable o, Object arg) {

		if (arg instanceof Boolean) {
			if ((Boolean) arg == true) {
				acertos += 1;
				pontuacao(true);
				remainTime = 0.2d;
				// notifyObservers((String) ("CERTO " + df.format(pontuacao)));
				notifyObservers((String) ("CERTO " + pontuacao));
			} else {
				acertos = 0;
				vidas -= 1;
				pontuacao(false);
				remainTime = 0.2d;
				notifyObservers((String) ("ERRADO " + pontuacao));
			}
		}

	}
}
