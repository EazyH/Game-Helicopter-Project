package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

// Nie generowa�em do tej klasy javadoca, poniewa� jest to w zasadzie klasa ma�o istotna
// Pos�u�y�a mi ona do zabawy ze stopowanie gry po klikni�ciu start, aby gracz m�g� si� zapozna� z pierwsz� przeszkod�.
public class Stop{

	ActionListener listen;
	Timer timer;
	
	public Stop(Thread t){
		
		listen = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				t.suspend();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				t.resume();
				
			}
				
		};
		
		timer = new Timer(0, listen);
		timer.setRepeats(false);
		timer.start();
		
	}
	
}
