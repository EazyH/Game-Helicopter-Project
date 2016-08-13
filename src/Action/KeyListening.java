package Action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import GamePlay.Helicopter;

// Klasa dodana podczas oddawania projektu.
// Wed³ug prowadz¹cego helikopter mia³ siê cofaæ po naciœniêciu wybranego klawisza, miniête przeszkody mia³y wracaæ, bonusy mia³y siê cofaæ, punkty mia³y siê odejmowaæ.
// Poszed³em na ³atwiznê i zrobi³em tylko cofanie siê helikoptera :)
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
