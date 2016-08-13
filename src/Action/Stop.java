package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

// Nie generowa³em do tej klasy javadoca, poniewa¿ jest to w zasadzie klasa ma³o istotna
// Pos³u¿y³a mi ona do zabawy ze stopowanie gry po klikniêciu start, aby gracz móg³ siê zapoznaæ z pierwsz¹ przeszkod¹.
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
