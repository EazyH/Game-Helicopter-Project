package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, która reprezentuje helikopter (obiekt klasy Helicopter). 
 *  @author Hubert Go³aszewski
 */
public class Helicopter {

	/** Grafika reprezentuj¹ca wznosz¹cy siê helikopter. */
	public static BufferedImage helicopterAscending;
	/** Grafika reprezentuj¹ca opadaj¹cy helikopter. */
	public static BufferedImage helicopterDescending;
	/** Grafika reprezentuj¹ca uszkodzony helikopter. */
	public static BufferedImage helicopterCrashed;
	/** Zmienna wskazuj¹ca pozycjê helikoptera w poziomie. */ 
	private static int xPosition;
	/** Zmienna wskazuj¹ca pozycjê helikoptera w pionie. */ 
	private static int yPosition;
	/** Zmienna przechowuj¹ca iloœæ punktów zdobytych przez helikopter. */
	private static int score;
	/** Zmienna mówi¹ca o tym, czy helikopter jest uszkodzony. */
	private static boolean crash;
	/** Zmienna mówi¹ca o tym, czy helikopter w³aœnie siê wznosi czy opada. */
	private static boolean ascending;
	// Zmienna dodana podczas oddawnia projektu
	public static boolean backward;
	
	/** Konstruktor klasy, który ustawia wartoœci pocz¹tkowe pozycji helikoptera, zeruje zdobyte punkty, ustala stan pocz¹tkowy lotu oraz ³aduje poszczególne grafiki. */ 
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

			System.err.println("B³¹d odczytu helikoptera wznosz¹cego siê!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/HelicopterDesc.png");

		try{

			helicopterDescending = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu helikoptera opadaj¹cego!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/HelicopterCrashed.png");

		try{

			helicopterCrashed = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu helikoptera rozbitego!");
			e.printStackTrace();

		}

	}

	/** Metoda zwiêkszaj¹ca zdobyte punkty o zadan¹ wartoœæ. 
	 *  @param score - Punkty, które nale¿y dodaæ do ca³ej puli.
	 */
	public void addScore(int score){

		this.score += score;

	}

	/** Metoda ustawiaj¹ca odpowiedni stan helikoptera. 
	 *  @param state - Stan jaki ma przyj¹æ helikopter.
	 */
	public void setState(String state){

		if(state == "crash") this.crash = true;
		else if (state == "asc") this.ascending = true;
		else if (state == "desc") this.ascending = false;

	}

	/** Metoda zwiêkszaj¹ca pozycjê w pionie helikoptera o zadan¹ wartoœæ.
	 *  @param y - Wartoœæ o jak¹ helikopter ma zwiêkszyæ swoje po³o¿enie w pionie. 
	 */
	public void setYUp(int y){

		this.yPosition += y;

	}

	/** Metoda zmniejszaj¹c¹ pozycjê w pionie helikoptera o zadan¹ wartoœæ.
	 *  @param y - Wartoœæ o jak¹ helikopter ma zmniejszyæ swoje po³o¿enie w pionie. 
	 */
	public void setYDown(int y){

		this.yPosition -= y;

	}

	/** Metoda zwiêkszaj¹ca pozycjê helikoptera w poziomie. 
	 *  @param x - Wartoœæ o jak¹ helikopter ma zwiêkszyæ swoje po³o¿enie w poziomie.
	 */
	public void setXUp(int x){

		this.xPosition += x;

	}

	/** Metoda zwracaj¹ca pozycjê helikoptera w pionie. 
	 *  @return Zwraca pozycjê helikoptera w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda zwracaj¹ca pozycjê helikoptera w poziomie.
	 *  @return Zwraca pozycjê helikoptera w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zwracaj¹ca iloœæ zdobytych punktów.
	 *  @return Zwraca iloœæ zdobytych punktów.
	 */
	public int getScore(){

		return this.score;

	}

	/** Metoda zwracaj¹ca stan rozbicia helikoptera.
	 *  @return Zwraca informacjê czy helikopter jest aktualnie rozbity.
	 */
	public boolean getCrashState(){

		return this.crash; 

	}

	/** Metoda zwracaj¹ca stan wznoszenia/opadania.
	 *  @return Zwraca informacjê czy helikopter w³aœnie opada czy siê wznosi.
	 */
	public boolean getFlyState(){

		return this.ascending; 

	}

}

