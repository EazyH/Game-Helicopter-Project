package Action;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GamePlay.Helicopter;

/**
 * Klasa rozszerzaj�ca klas� MouseListener, kt�ra ma za zadanie wy�apywa� dane akcje zwi�zane z myszk� i odpowiednio na nie reagowa�
 * @author Hubert Go�aszewski
 */
public class MouseListening implements MouseListener{

	/** Referencja na obiekt klasy Helikopter. */
	private static Helicopter helicopter;

	/** Konstruktor klasy, kt�ry przypisuje do referencji utworzony wcze�niej helikopter (obiekt klasy Helicopter).
	 *  @param helicopter - referencja do utworzonego wcze�niej helikoptera (obiektu klasy Helicopter).
	 */
	public MouseListening(Helicopter helicopter){

		this.helicopter = helicopter;

	}

	/** Metoda uruchamiana w momencie klikni�cia (przyci�ni�cia i odci�ni�cia) kt�regokolwiek przycisku myszki. */
	public void mouseClicked(MouseEvent e) {
	}

	/** Metoda uruchamiana podczas wej�cia kursora na obszar okna. */
	public void mouseEntered(MouseEvent e) {	
	}

	/** Metoda uruchamiana podczas wyj�cia kursora poza obszar okna. */
	public void mouseExited(MouseEvent e) {
	}

	/** Metoda uruchamiana w momencie przyci�ni�cia kt�regokolwiek przycisku myszki.
	 *  Zmienia stan helikoptera (obiektu klasy Helicopter) na wznosz�cy.
	 */
	public void mousePressed(MouseEvent e) {

		// Zmiana stanu helikoptera na wznosz�cy si�
		helicopter.setState("asc");

	}

	/** Metoda uruchamiana w momencie odci�ni�cia kt�regokolwiek przycisku myszki.
	 *  Zmienia stan helikoptera (obiektu klasy Helicopter) na opadaj�cy.
	 */
	public void mouseReleased(MouseEvent e) {

		// Zmiana stanu helikoptera na opadaj�cy
		helicopter.setState("desc");

	}

}
