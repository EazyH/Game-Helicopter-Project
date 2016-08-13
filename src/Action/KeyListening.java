package Action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import GamePlay.Helicopter;

// Klasa dodana podczas oddawania projektu.
// Wed�ug prowadz�cego helikopter mia� si� cofa� po naci�ni�ciu wybranego klawisza, mini�te przeszkody mia�y wraca�, bonusy mia�y si� cofa�, punkty mia�y si� odejmowa�.
// Poszed�em na �atwizn� i zrobi�em tylko cofanie si� helikoptera :)
public class KeyListening implements KeyListener {
	
	public static Helicopter helicopter;
	
	public void keyPressed(KeyEvent e) {
		
		helicopter.backward = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		helicopter.backward = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
