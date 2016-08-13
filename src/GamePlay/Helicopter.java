package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, kt�ra reprezentuje helikopter (obiekt klasy Helicopter). 
 *  @author Hubert Go�aszewski
 */
public class Helicopter {

	/** Grafika reprezentuj�ca wznosz�cy si� helikopter. */
	public static BufferedImage helicopterAscending;
	/** Grafika reprezentuj�ca opadaj�cy helikopter. */
	public static BufferedImage helicopterDescending;
	/** Grafika reprezentuj�ca uszkodzony helikopter. */
	public static BufferedImage helicopterCrashed;
	/** Zmienna wskazuj�ca pozycj� helikoptera w poziomie. */ 
	private static int xPosition;
	/** Zmienna wskazuj�ca pozycj� helikoptera w pionie. */ 
	private static int yPosition;
	/** Zmienna przechowuj�ca ilo�� punkt�w zdobytych przez helikopter. */
	private static int score;
	/** Zmienna m�wi�ca o tym, czy helikopter jest uszkodzony. */
	private static boolean crash;
	/** Zmienna m�wi�ca o tym, czy helikopter w�a�nie si� wznosi czy opada. */
	private static boolean ascending;
	// Zmienna dodana podczas oddawnia projektu
	public static boolean backward;
	
	/** Konstruktor klasy, kt�ry ustawia warto�ci pocz�tkowe pozycji helikoptera, zeruje zdobyte punkty, ustala stan pocz�tkowy lotu oraz �aduje poszczeg�lne grafiki. */ 
	public Helicopter(){

		backward = false;
		score = 0;
		xPosition = 150;
		yPosition = 150;
		crash = false;
		ascending = false;

		File imageFile = new File("Resources/HelicopterAsc.png");

		try{

			helicopterAscending = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu helikoptera wznosz�cego si�!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/HelicopterDesc.png");

		try{

			helicopterDescending = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu helikoptera opadaj�cego!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/HelicopterCrashed.png");

		try{

			helicopterCrashed = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu helikoptera rozbitego!");
			e.printStackTrace();

		}

	}

	/** Metoda zwi�kszaj�ca zdobyte punkty o zadan� warto��. 
	 *  @param score - Punkty, kt�re nale�y doda� do ca�ej puli.
	 */
	public void addScore(int score){

		this.score += score;

	}

	/** Metoda ustawiaj�ca odpowiedni stan helikoptera. 
	 *  @param state - Stan jaki ma przyj�� helikopter.
	 */
	public void setState(String state){

		if(state == "crash") this.crash = true;
		else if (state == "asc") this.ascending = true;
		else if (state == "desc") this.ascending = false;

	}

	/** Metoda zwi�kszaj�ca pozycj� w pionie helikoptera o zadan� warto��.
	 *  @param y - Warto�� o jak� helikopter ma zwi�kszy� swoje po�o�enie w pionie. 
	 */
	public void setYUp(int y){

		this.yPosition += y;

	}

	/** Metoda zmniejszaj�c� pozycj� w pionie helikoptera o zadan� warto��.
	 *  @param y - Warto�� o jak� helikopter ma zmniejszy� swoje po�o�enie w pionie. 
	 */
	public void setYDown(int y){

		this.yPosition -= y;

	}

	/** Metoda zwi�kszaj�ca pozycj� helikoptera w poziomie. 
	 *  @param x - Warto�� o jak� helikopter ma zwi�kszy� swoje po�o�enie w poziomie.
	 */
	public void setXUp(int x){

		this.xPosition += x;

	}

	/** Metoda zwracaj�ca pozycj� helikoptera w pionie. 
	 *  @return Zwraca pozycj� helikoptera w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda zwracaj�ca pozycj� helikoptera w poziomie.
	 *  @return Zwraca pozycj� helikoptera w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zwracaj�ca ilo�� zdobytych punkt�w.
	 *  @return Zwraca ilo�� zdobytych punkt�w.
	 */
	public int getScore(){

		return this.score;

	}

	/** Metoda zwracaj�ca stan rozbicia helikoptera.
	 *  @return Zwraca informacj� czy helikopter jest aktualnie rozbity.
	 */
	public boolean getCrashState(){

		return this.crash; 

	}

	/** Metoda zwracaj�ca stan wznoszenia/opadania.
	 *  @return Zwraca informacj� czy helikopter w�a�nie opada czy si� wznosi.
	 */
	public boolean getFlyState(){

		return this.ascending; 

	}

}

