package Action;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GamePlay.Helicopter;

/**
 * Klasa rozszerzaj¹ca klasê MouseListener, która ma za zadanie wy³apywaæ dane akcje zwi¹zane z myszk¹ i odpowiednio na nie reagowaæ
 * @author Hubert Go³aszewski
 */
public class MouseListening implements MouseListener{

	/** Referencja na obiekt klasy Helikopter. */
	private static Helicopter helicopter;

	/** Konstruktor klasy, który przypisuje do referencji utworzony wczeœniej helikopter (obiekt klasy Helicopter).
	 *  @param helicopter - referencja do utworzonego wczeœniej helikoptera (obiektu klasy Helicopter).
	 */
	public MouseListening(Helicopter helicopter){

		this.helicopter = helicopter;

	}

	/** Metoda uruchamiana w momencie klikniêcia (przyciœniêcia i odciœniêcia) któregokolwiek przycisku myszki. */
	public void mouseClicked(MouseEvent e) {
	}

	/** Metoda uruchamiana podczas wejœcia kursora na obszar okna. */
	public void mouseEntered(MouseEvent e) {	
	}

	/** Metoda uruchamiana podczas wyjœcia kursora poza obszar okna. */
	public void mouseExited(MouseEvent e) {
	}

	/** Metoda uruchamiana w momencie przyciœniêcia któregokolwiek przycisku myszki.
	 *  Zmienia stan helikoptera (obiektu klasy Helicopter) na wznosz¹cy.
	 */
	public void mousePressed(MouseEvent e) {

		// Zmiana stanu helikoptera na wznosz¹cy siê
		helicopter.setState("asc");

	}

	/** Metoda uruchamiana w momencie odciœniêcia któregokolwiek przycisku myszki.
	 *  Zmienia stan helikoptera (obiektu klasy Helicopter) na opadaj¹cy.
	 */
	public void mouseReleased(MouseEvent e) {

		// Zmiana stanu helikoptera na opadaj¹cy
		helicopter.setState("desc");

	}

}
