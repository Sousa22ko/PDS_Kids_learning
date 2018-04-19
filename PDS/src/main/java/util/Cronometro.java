package util;

import java.util.Observable;
import java.util.Observer;

//Fun fact: cronômetro em ingles é "cronometro"
public class Cronometro extends Observable implements Runnable, Observer {

	private Double remainTime = 30.0d;
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
		// TODO Auto-generated method stub
		while (true) {
			if (remainTime == 0.0d)
				notifyObservers(0);
			else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remainTime -= 0.1d;
				notifyObservers(100 + remainTime);
			}
		}
	}

	public void update(Observable o, Object arg) {
		if ((Boolean) arg) {
			if (acertos < 10) {
				remainTime += 5;
				notifyObservers(5);
			}
			if (acertos >= 10 && acertos <= 15) {
				remainTime += 2;
				notifyObservers(2);
			}
			if (acertos >= 15) {
				remainTime += 1;
				notifyObservers(1);
			}
		}
		else{
			remainTime -= 1;
			notifyObservers(-1);
		}
	}
}
