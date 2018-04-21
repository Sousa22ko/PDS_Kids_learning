package util;

import java.util.Observable;
import java.util.Observer;


public class Relogio extends Observable implements Runnable, Observer {

	private Double remainTime = 0.2d;
	private int acertos = 0;
	private double pontuacao = 0;
	private boolean rodaThread = true;
	
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
		while (rodaThread) {
			setChanged();
			if (remainTime < 0.1d){
				notifyObservers((Boolean)false);
			}
			else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				remainTime += 0.1000000000d;
				notifyObservers((Double)remainTime);
				System.out.println((Double)remainTime);
			}
		}
	}
	
	public void calculaPontuacao(boolean arg){
		if(arg){
			if(acertos <= 5 && arg)
				pontuacao += (1000 - ((10-acertos)*remainTime));
			else
				pontuacao += (1000 - ((5)*remainTime));
		}
		else{
			if(pontuacao >= 100)
				pontuacao -= 100;
			else
				pontuacao = 0;
		}
	}

	public void update(Observable o, Object arg) {
		setChanged();
		if (arg instanceof String){
			String compair[] = ((String) arg).split(" ");
			if (Boolean.parseBoolean(compair[0])) {
				acertos += 1;
				calculaPontuacao(true);
				remainTime = 0.2d;
				notifyObservers((String)("CERTO "+pontuacao));
			}
			else{
				acertos = 0;
				calculaPontuacao(false);
				remainTime = 0.2d;
				notifyObservers((String)("ERRADO "+pontuacao));
			}
			
			System.out.println("O VALOR DE VIDAS É "+compair[1]);
			if(compair[1].equals("1")){
				System.out.println("ENTRANDO NO EQUALS É PRA PARAR O RELOGINHO");
				rodaThread = false;
			}
		}
	}
}
