package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, która reprezentuje przeszkodê (obiekt typu Obstackle).
 *  @author Hubert Go³aszewski
 */
public class Obstackle {

	/** Grafika reprezentuj¹ca przeszkodê dla poziomu 1. */
	public static BufferedImage level1;
	/** Grafika reprezentuj¹ca przeszkodê dla poziomu 2. */
	public static BufferedImage level2;
	/** Grafika reprezentuj¹ca przeszkodê dla poziomu 3. */
	public static BufferedImage level3;
	/** Zmienna wskazuj¹ca pozycjê przeszkody w poziomie. */ 
	private static int xPosition; 
	/** Zmienna wskazuj¹ca pozycjê przeszkody w pionie. */ 
	private static int yPosition;  

	/** Konstruktor klasy, który inizjalizuje zmienne pozycji oraz wczytuje grafiki przeszkód dla poszczególnych poziomów. */
	public Obstackle(){

		xPosition = 700;
		yPosition = 330;

		File imageFile = new File("Resources/Level1.png");

		try{

			level1 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu przeszkody do poziomu 1!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/Level2.png");

		try{

			level2 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu przeszkody do poziomu 2!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/Level3.png");

		try{

			level3 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu przeszkody do poziomu 3!");
			e.printStackTrace();

		}

	}

	/** Metoda zwracaj¹ca pozycjê przeszkody w pionie. 
	 *  @return Zwraca aktualna pozycjê przeszkody w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda zwracaj¹ca pozycjê przeszkody w poziomie.
	 *  @return Zwraca aktualn¹ pozycje przeszkody w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zmniejszaj¹ca pozycjê helikoptera w poziomie.
	 *  @param x - Wartoœæ o jak¹ zmniejszona ma zostaæ pozycja helikoptera w poziomie.
	 */
	public void setXDown(int x){

		this.xPosition -= x;
	}

	/** Metoda ustawiaj¹ca pozycjê pocz¹tkow¹ dla ka¿dej nowej przeszkody.
	 *  @param x - Pozycja przeszkody w poziomie.
	 *  @param y - Pozycja przeszkody w pionie (losowa).
	 */
	public void setStartingPosition(int x, int y){

		this.xPosition = x;
		this.yPosition = y;

	}

	// Metoda dodana podczas oddawania projektu
	public void setXUp(int x){

		this.xPosition += x;
		
	}
	
}
