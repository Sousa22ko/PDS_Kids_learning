package util;

import java.util.Observable;
import java.util.Observer;

//Fun fact: cronômetro em ingles é "cronometro"
public class Cronometro extends Observable implements Runnable, Observer {

	private Double remainTime;
	private int acertos = 0;
	private double pontuacao = 0;
	private boolean dir;
	private int vidas = 3;

	@SuppressWarnings("unused")
	private Observable obs;

	public Cronometro(Observable obs) {
		assinar(obs);
		dir = SharedInfo.getDirection();
		if(dir)
			remainTime = 0.2d;
		else
			remainTime = 45.0d;
	}
	
	public void reloadRelogio(){
		dir = SharedInfo.getDirection();
		if(dir)
			remainTime = 0.2d;
		else
			remainTime = 45.0d;
	}

	public void resetCron() {
		if(dir)
			remainTime = 0.2d;
		else
			remainTime = 45.0d;
		acertos = 0;
	}

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
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
				if (dir)
					remainTime += 0.1000000000d;
				else
					remainTime -= 0.1000000000d;
				notifyObservers((Double) remainTime);
				// System.out.println((Double)remainTime);
			}
		}
	}

	public void calculaPontuacao(boolean arg) {
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
		if (dir) {
			if (arg instanceof Boolean) {
				if ((Boolean)arg == true) {
					acertos += 1;
					calculaPontuacao(true);
					remainTime = 0.2d;
					//notifyObservers((String) ("CERTO " + df.format(pontuacao)));
					notifyObservers((String) ("CERTO " + pontuacao));
				} else {
					acertos = 0;
					vidas -=1;
					calculaPontuacao(false);
					remainTime = 0.2d;
					notifyObservers((String) ("ERRADO " + pontuacao));
				}
			}

		} else {
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
}
