package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, która reprezentuje mapê (obiekt klasy Map).
 * @author Hubert Go³aszewski
 */
public class Map {

	/** Grafika reprezentuj¹ca mapê dla poziomu 1. */
	public static BufferedImage map1;
	/** Grafika reprezentuj¹ca mapê dla poziomu 2. */
	public static BufferedImage map2;
	/** Grafika reprezentuj¹ca mapê dla poziomu 3. */
	public static BufferedImage map3;
	/** Grafika reprezentuj¹ca napis koñca gry. */
	public static BufferedImage gameOver;
	/** Zmienna informuj¹ca o obecnym poziomie trudnoœci mapy. */
	public static int level;
	/** Zmienna informuj¹ca o tym, czy na mapie powinien pokazaæ siê bonus. */
	private static boolean rocketON;
	/** Zmienna informuj¹ca o tym, czy na mapie przelecia³a ju¿ przeszkoda z poprzedniego poziomu po zmianie poziomów. */
	private static boolean firstChange;

	/** Konstruktor, który zeruje poziom, inizjalizuje zmienne informuj¹ce o stanie mapy oraz wczytuje grafiki dla poszczególnych poziomów. */
	public Map(){

		level = 0;
		rocketON = false;
		firstChange = false;

		File imageFile = new File("Resources/MapLevel1.png");

		try{

			map1 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu mapy do poziomu 1!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/MapLevel2.png");

		try{

			map2 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu mapy do poziomu 2!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/MapLevel3.png");

		try{

			map3 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu mapy do poziomu 3!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/GameOver.png");

		try{

			gameOver = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu napisu GameOver!");
			e.printStackTrace();

		}

	}

	/** Metoda ustawiaj¹ca poziom trudnoœci mapy.
	 *  @param level - Wartoœæ na jak¹ ma siê zmieniæ poziom mapy.
	 */
	public void setLevel(int level){

		this.level = level;

	}

	/** Metoda zwracaj¹ca obecny poziom mapy. 
	 *  @return Zwraca obecny poziom mapy.
	 */
	public int getLevel(){

		return this.level;

	}

	/** Metoda aktywuj¹ca/dezaktywuj¹ca bonus. 
	 *  @param mode - Tryb w jakim ma zostaæ ustawiony bonus.
	 */
	public void setBonus(String mode){

		if(mode == "ON") this.rocketON = true;
		else if(mode == "OFF") this.rocketON = false;

	}

	/** Metoda zwracaj¹ca stan bonusa. 
	 *  @return Zwraca obecny stan bonusa.
	 */
	public boolean getBonusState(){

		return this.rocketON;

	}

	/** Metoda ustawiaj¹ca flagê zmiany poziomu.
	 *  @param mode - Tryb w jakim ma zostaæ ustawiona flaga zmiany poziomu.
	 */
	public void setMapChange(String mode){

		if(mode == "ON") this.firstChange = true;
		else if(mode == "OFF") this.firstChange = false;

	}

	/** Metoda zwracaj¹ca flagê zmiany poziomu. 
	 *  @return Zwraca flagê poziomu mapu. 
	 */
	public boolean getMapChange(){

		return this.firstChange;

	}

}
