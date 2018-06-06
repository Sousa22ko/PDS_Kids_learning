package util;

import java.util.Observable;
import java.util.Observer;

public abstract class Timer extends Observable implements Runnable, Observer {

	protected Double remainTime;
	protected int acertos = 0;
	protected int erros = 0;
	protected double pontuacao = 0;
	protected boolean dir;
	protected int vidas = 3;
	protected Observable obs;

	public abstract void reloadRelogio(); // zera o relogio
	
	public Timer(Observable obs){
		assinar(obs);
	}

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	public abstract void run(); // sleep while true
	//public abstract void pontuacao(boolean arg); // calcula pontuação
	public abstract void update(Observable o, Object arg); // ação quando recebe
															// notificação
}
