package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, kt�ra reprezentuje map� (obiekt klasy Map).
 * @author Hubert Go�aszewski
 */
public class Map {

	/** Grafika reprezentuj�ca map� dla poziomu 1. */
	public static BufferedImage map1;
	/** Grafika reprezentuj�ca map� dla poziomu 2. */
	public static BufferedImage map2;
	/** Grafika reprezentuj�ca map� dla poziomu 3. */
	public static BufferedImage map3;
	/** Grafika reprezentuj�ca napis ko�ca gry. */
	public static BufferedImage gameOver;
	/** Zmienna informuj�ca o obecnym poziomie trudno�ci mapy. */
	public static int level;
	/** Zmienna informuj�ca o tym, czy na mapie powinien pokaza� si� bonus. */
	private static boolean rocketON;
	/** Zmienna informuj�ca o tym, czy na mapie przelecia�a ju� przeszkoda z poprzedniego poziomu po zmianie poziom�w. */
	private static boolean firstChange;

	/** Konstruktor, kt�ry zeruje poziom, inizjalizuje zmienne informuj�ce o stanie mapy oraz wczytuje grafiki dla poszczeg�lnych poziom�w. */
	public Map(){

		level = 0;
		rocketON = false;
		firstChange = false;

		File imageFile = new File("Resources/MapLevel1.png");

		try{

			map1 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu mapy do poziomu 1!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/MapLevel2.png");

		try{

			map2 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu mapy do poziomu 2!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/MapLevel3.png");

		try{

			map3 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu mapy do poziomu 3!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/GameOver.png");

		try{

			gameOver = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu napisu GameOver!");
			e.printStackTrace();

		}

	}

	/** Metoda ustawiaj�ca poziom trudno�ci mapy.
	 *  @param level - Warto�� na jak� ma si� zmieni� poziom mapy.
	 */
	public void setLevel(int level){

		this.level = level;

	}

	/** Metoda zwracaj�ca obecny poziom mapy. 
	 *  @return Zwraca obecny poziom mapy.
	 */
	public int getLevel(){

		return this.level;

	}

	/** Metoda aktywuj�ca/dezaktywuj�ca bonus. 
	 *  @param mode - Tryb w jakim ma zosta� ustawiony bonus.
	 */
	public void setBonus(String mode){

		if(mode == "ON") this.rocketON = true;
		else if(mode == "OFF") this.rocketON = false;

	}

	/** Metoda zwracaj�ca stan bonusa. 
	 *  @return Zwraca obecny stan bonusa.
	 */
	public boolean getBonusState(){

		return this.rocketON;

	}

	/** Metoda ustawiaj�ca flag� zmiany poziomu.
	 *  @param mode - Tryb w jakim ma zosta� ustawiona flaga zmiany poziomu.
	 */
	public void setMapChange(String mode){

		if(mode == "ON") this.firstChange = true;
		else if(mode == "OFF") this.firstChange = false;

	}

	/** Metoda zwracaj�ca flag� zmiany poziomu. 
	 *  @return Zwraca flag� poziomu mapu. 
	 */
	public boolean getMapChange(){

		return this.firstChange;

	}

}
