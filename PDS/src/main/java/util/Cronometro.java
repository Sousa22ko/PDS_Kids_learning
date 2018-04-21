package util;

import java.util.Observable;
import java.util.Observer;

//Fun fact: cronômetro em ingles é "cronometro"
public class Cronometro extends Observable implements Runnable, Observer {

	private Double remainTime = 45.0d;
	private int acertos = 0;
	
	@SuppressWarnings("unused")
	private Observable obs;

	
	public Cronometro(Observable obs){
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
				remainTime -= 0.1000000000d;
				notifyObservers((Double)remainTime);
				System.out.println((Double)remainTime);
			}
		}
	}

	public void update(Observable o, Object arg) {
		setChanged();
		if ((Boolean) arg) {
			acertos += 1;
			if (acertos < 10) {
				remainTime += 5;
				notifyObservers((String)("" +5));
			}
			if (acertos >= 10 && acertos <= 15) {
				remainTime += 2;
				notifyObservers((String)("" + 2));
			}
			if (acertos >= 15) {
				remainTime += 1;
				notifyObservers((String)("" + 1));
			}
		}
		else{
			remainTime -= 3;
			notifyObservers((String)("" +(-3)));
		}
	}
}
