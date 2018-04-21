package util;

import java.util.Observable;
import java.util.Observer;


public class Relogio extends Observable implements Runnable, Observer {

	private Double remainTime = 0.2d;
	private int acertos = 0;
	
	@SuppressWarnings("unused")
	private Observable obs;

	
	public Relogio(Observable obs){
		assinar(obs);
	}
	
	public void resetCron() {
		remainTime = 30.0d;
		acertos = 0;
	}

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	public void run() {
		while (true) {
			setChanged();
			if (remainTime < 0.1d)
				notifyObservers((Boolean)false);
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remainTime += 0.1000000000d;
				notifyObservers((Double)remainTime);
				System.out.println((Double)remainTime);
			}
		}
	}

	public void update(Observable o, Object arg) {
		setChanged();
		if ((Boolean) arg) {
			acertos += 1;
			remainTime = 0.2;
			notifyObservers((String)("CERTO")); //Codigo de acerto = 5
		}
		else{
			remainTime = 0.2;
			notifyObservers((String)("ERRADO")); //Codigo de erro = -3
		}
	}
}
