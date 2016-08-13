package Action;
import GamePlay.Helicopter;
import GamePlay.Map;
import GamePlay.Obstackle;
import GamePlay.Rocket;
import Graphics.ActionPanel;
import Graphics.Window;

/** Klasa implementuj¹ca interfejs Runnable, która powo³uje oddzielny w¹tek w celu ci¹g³ej zmiany pozycji helikoptera, przeszkód i bonusa.
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

	/** Konstruktor klasy, który przypisuje do referencji utworzone wczeœniej obiekty tj. helikopter, przeszkoda oraz bonus.
	 *  @param helicopter - Referencja do utworzonego wczeœniej helikoptera (obiektu klasy Helicopter).
	 *  @param obstackle - Referencja do utworzonej wczeœniej przeszkody (obiektu klasy Obstackle).
	 *  @param rocket - Referencja do utworzonego wczeœniej bonusu (obiektu klasy Rocket).
	 *  @param map - Referencja do utworzonej wczeœniej mapy (obiekt klasy Map).
	 */
	public Flying(Helicopter helicopter, Obstackle obstackle, Rocket rocket, Map map){

		this.helicopter = helicopter;
		this.obstackle = obstackle;
		this.rocket = rocket;
		this.map = map;

	}

	/** Metoda zmieniaj¹ca po³o¿enie helikoptera i uzyskuj¹ca odpowiedni¹ trajektoriê ostatniego lotu po kolizji. */
	private void lastFly(){

		// Zapewnienie odpowiedniej trajektorii lotu rozbitego helikoptera
		helicopter.setYUp(1);
		helicopter.setXUp(1);

		// Przemaluj panel
		Window.actionPanel.repaint();

	}

	/** Metoda zmieniaj¹ca po³o¿enie helikoptera i uzyskuj¹ca odpowiedni¹ trajektoriê wznoszenia. */
	private void descend(){

		for(int i = 0; i < 10; i++){

			// Zapewnienie odpowiedniej trajektorii lotu helikoptera
			if(i == 6) helicopter.setYUp(1);
			if(i == 7) helicopter.setYUp(1);
			else if(i == 8) helicopter.setYUp(2);
			else if(i == 9) helicopter.setYUp(2);
			// Przesuniêcie przeszkody
			// Przesuniêcie przeszkody (if dodany podczas oddawania projektu)
			if(helicopter.backward == true){
							
				obstackle.setXUp(1);
				
			}
			else{
							
				obstackle.setXDown(1);
						
			}
			// Je¿eli wyst¹pi³a kolizja to przerwij normalne spadanie 
			if(helicopter.getCrashState()) break;
			// Przemaluj panel
			Window.actionPanel.repaint();
			// Dostosuj prêdkoœæ lotu
			slow(2);
			// Przesuniêcie bonusa je¿eli jest aktywny
			if(map.getBonusState()) rocket.setXDown(1);

		}

	}

	/** Metoda zmieniaj¹ca po³o¿enie helikoptera i uzyskuj¹ca odpowiedni¹ trajektoriê opadania. */
	private void ascend(){

		for(int i = 0; i < 9; i++){

			// Zapewnienie odpowiedniej trajektorii lotu helikoptera
			if(i == 4) helicopter.setYDown(1);
			else if(i == 5) helicopter.setYDown(1);
			else if(i == 7) helicopter.setYDown(1); 
			else if(i == 8) helicopter.setYDown(2); 
			// Przesuniêcie przeszkody (if dodany podczas oddawania projektu)
			if(helicopter.backward == true){
				
				obstackle.setXUp(1);
			}
			else{
				
				obstackle.setXDown(1);
			
			}
			// Je¿eli wyst¹pi³a kolizja to przerwij wznoszenie 
			if(helicopter.getCrashState()) break;
			// Przemaluj panel
			Window.actionPanel.repaint();
			// Dostosuj prêdkoœæ lotu
			slow(2);
			// Przesuniêcie bonusa
			if(map.getBonusState()) rocket.setXDown(1);

		}

	}

	/** Metoda usypiaj¹ca w¹tek w celu uzyskania poprawnej i p³ynnej animacji ruchu helikoptera.
	 *  @param miliSeconds - Czas w milisekundach na jaki w¹tek ma usn¹æ
	 *  */
	private void slow(int miliSeconds){

		try{

			Thread.sleep(miliSeconds); 

		} 
		catch (InterruptedException e){

			e.printStackTrace();

		}

	}

	/** Metoda uruchamiaj¹ca siê automatycznie na rzecz nowo utworzonego w¹tku. */
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
