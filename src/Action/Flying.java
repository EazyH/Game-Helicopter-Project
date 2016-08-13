package Action;
import GamePlay.Helicopter;
import GamePlay.Map;
import GamePlay.Obstackle;
import GamePlay.Rocket;
import Graphics.ActionPanel;
import Graphics.Window;

/** Klasa implementuj�ca interfejs Runnable, kt�ra powo�uje oddzielny w�tek w celu ci�g�ej zmiany pozycji helikoptera, przeszk�d i bonusa.
 * @author Hubert
 */
public class Flying implements Runnable{

	/** Referencja na obiekt klasy Helicopter. */
	private static Helicopter helicopter;
	/** Referencja na obiekt klasy Obstackle. */
	private static Obstackle obstackle;
	/** Referencja na obiekt klasy Rocket. */
	private static Rocket rocket;
	/** Referencja na obiekt klasy Map. */
	private static Map map;

	/** Konstruktor klasy, kt�ry przypisuje do referencji utworzone wcze�niej obiekty tj. helikopter, przeszkoda oraz bonus.
	 *  @param helicopter - Referencja do utworzonego wcze�niej helikoptera (obiektu klasy Helicopter).
	 *  @param obstackle - Referencja do utworzonej wcze�niej przeszkody (obiektu klasy Obstackle).
	 *  @param rocket - Referencja do utworzonego wcze�niej bonusu (obiektu klasy Rocket).
	 *  @param map - Referencja do utworzonej wcze�niej mapy (obiekt klasy Map).
	 */
	public Flying(Helicopter helicopter, Obstackle obstackle, Rocket rocket, Map map){

		this.helicopter = helicopter;
		this.obstackle = obstackle;
		this.rocket = rocket;
		this.map = map;

	}

	/** Metoda zmieniaj�ca po�o�enie helikoptera i uzyskuj�ca odpowiedni� trajektori� ostatniego lotu po kolizji. */
	private void lastFly(){

		// Zapewnienie odpowiedniej trajektorii lotu rozbitego helikoptera
		helicopter.setYUp(1);
		helicopter.setXUp(1);

		// Przemaluj panel
		Window.actionPanel.repaint();

	}

	/** Metoda zmieniaj�ca po�o�enie helikoptera i uzyskuj�ca odpowiedni� trajektori� wznoszenia. */
	private void descend(){

		for(int i = 0; i < 10; i++){

			// Zapewnienie odpowiedniej trajektorii lotu helikoptera
			if(i == 6) helicopter.setYUp(1);
			if(i == 7) helicopter.setYUp(1);
			else if(i == 8) helicopter.setYUp(2);
			else if(i == 9) helicopter.setYUp(2);
			// Przesuni�cie przeszkody
			// Przesuni�cie przeszkody (if dodany podczas oddawania projektu)
			if(helicopter.backward == true){
							
				obstackle.setXUp(1);
				
			}
			else{
							
				obstackle.setXDown(1);
						
			}
			// Je�eli wyst�pi�a kolizja to przerwij normalne spadanie 
			if(helicopter.getCrashState()) break;
			// Przemaluj panel
			Window.actionPanel.repaint();
			// Dostosuj pr�dko�� lotu
			slow(2);
			// Przesuni�cie bonusa je�eli jest aktywny
			if(map.getBonusState()) rocket.setXDown(1);

		}

	}

	/** Metoda zmieniaj�ca po�o�enie helikoptera i uzyskuj�ca odpowiedni� trajektori� opadania. */
	private void ascend(){

		for(int i = 0; i < 9; i++){

			// Zapewnienie odpowiedniej trajektorii lotu helikoptera
			if(i == 4) helicopter.setYDown(1);
			else if(i == 5) helicopter.setYDown(1);
			else if(i == 7) helicopter.setYDown(1); 
			else if(i == 8) helicopter.setYDown(2); 
			// Przesuni�cie przeszkody (if dodany podczas oddawania projektu)
			if(helicopter.backward == true){
				
				obstackle.setXUp(1);
			}
			else{
				
				obstackle.setXDown(1);
			
			}
			// Je�eli wyst�pi�a kolizja to przerwij wznoszenie 
			if(helicopter.getCrashState()) break;
			// Przemaluj panel
			Window.actionPanel.repaint();
			// Dostosuj pr�dko�� lotu
			slow(2);
			// Przesuni�cie bonusa
			if(map.getBonusState()) rocket.setXDown(1);

		}

	}

	/** Metoda usypiaj�ca w�tek w celu uzyskania poprawnej i p�ynnej animacji ruchu helikoptera.
	 *  @param miliSeconds - Czas w milisekundach na jaki w�tek ma usn��
	 *  */
	private void slow(int miliSeconds){

		try{

			Thread.sleep(miliSeconds); 

		} 
		catch (InterruptedException e){

			e.printStackTrace();

		}

	}

	/** Metoda uruchamiaj�ca si� automatycznie na rzecz nowo utworzonego w�tku. */
	public void run(){

		while(true){

			// Kolizja
			if(helicopter.getCrashState()){

				slow(5); 
				lastFly();

			}
			// Brak kolizji
			else{

				// Spadek
				if(!helicopter.getFlyState()){ 

					descend();

				}
				// Wznos
				else{ 

					ascend();

				}

			}

		}

	}

}
