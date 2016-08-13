package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Klasa, kt�ra reprezentuje przeszkod� (obiekt typu Obstackle).
 *  @author Hubert Go�aszewski
 */
public class Obstackle {

	/** Grafika reprezentuj�ca przeszkod� dla poziomu 1. */
	public static BufferedImage level1;
	/** Grafika reprezentuj�ca przeszkod� dla poziomu 2. */
	public static BufferedImage level2;
	/** Grafika reprezentuj�ca przeszkod� dla poziomu 3. */
	public static BufferedImage level3;
	/** Zmienna wskazuj�ca pozycj� przeszkody w poziomie. */ 
	private static int xPosition; 
	/** Zmienna wskazuj�ca pozycj� przeszkody w pionie. */ 
	private static int yPosition;  

	/** Konstruktor klasy, kt�ry inizjalizuje zmienne pozycji oraz wczytuje grafiki przeszk�d dla poszczeg�lnych poziom�w. */
	public Obstackle(){

		xPosition = 700;
		yPosition = 330;

		File imageFile = new File("Resources/Level1.png");

		try{

			level1 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu przeszkody do poziomu 1!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/Level2.png");

		try{

			level2 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu przeszkody do poziomu 2!");
			e.printStackTrace();

		}

		imageFile = new File("Resources/Level3.png");

		try{

			level3 = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu przeszkody do poziomu 3!");
			e.printStackTrace();

		}

	}

	/** Metoda zwracaj�ca pozycj� przeszkody w pionie. 
	 *  @return Zwraca aktualna pozycj� przeszkody w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda zwracaj�ca pozycj� przeszkody w poziomie.
	 *  @return Zwraca aktualn� pozycje przeszkody w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zmniejszaj�ca pozycj� helikoptera w poziomie.
	 *  @param x - Warto�� o jak� zmniejszona ma zosta� pozycja helikoptera w poziomie.
	 */
	public void setXDown(int x){

		this.xPosition -= x;
	}

	/** Metoda ustawiaj�ca pozycj� pocz�tkow� dla ka�dej nowej przeszkody.
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
