package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/** Klasa, kt�ra reprezentuje bonus (obiekt typu Rocket).
 * @author Hubert
 */
public class Rocket{

	/** Grafika reprezentuj�ca bonus. */
	public static BufferedImage bonus;
	/** Zmienna wskazuj�ca pozycj� przeszkody w poziomie. */ 
	private static int xPosition;
	/** Zmienna wskazuj�ca pozycj� przeszkody w pionie. */ 
	private static int yPosition;
	/** Referencja na obiekty typu Random. */
	private static Random randPosition;

	/** Konstruktor klasy, kt�ry ustawia pocz�tkowe pozycje bonusa (pozycja w pionie jest pseudolosowa) oraz wczytuje grafik� bonusa. */
	public Rocket(){

		randPosition = new Random();
		xPosition = 1200;
		yPosition = randPosition.nextInt(300) + 120;

		File imageFile = new File("Resources/Bonus.png");

		try{

			bonus = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu bonusa!");
			e.printStackTrace();

		}

	}

	/** Metoda zwracaj�ca pozycj� bonusa w poziomie. 
	 *  @return Zwraca akutaln� pozycj� bonusa w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zwracaj�ca pozycj� bonusa w pionie. 
	 *  @return Zwraca akutaln� pozycj� bonusa w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda ustawiaj�ca pozycj� bonusa w pionie. 
	 *  @param y - Warto�� na jak� ma zosta� ustawiona pozycja bonusa w pionie.
	 */
	public void setY(int y){

		this.yPosition = y;

	}

	/** Metoda ustawiaj�ca pozycj� bonusa w poziomie. 
	 *  @param x - Warto�� na jak� ma zosta� ustawiona pozycja bonusa w poziomie.
	 */
	public void setX(int x){

		this.xPosition = x;

	}

	/** Metoda zmniejszaj�ca pozycj� bonusa w poziomie. 
	 *  @param x - Warto�� o jak� ma zosta� zmniejszona pozycja bonusa w poziomie.
	 */
	public void setXDown(int x){

		this.xPosition -= x;
	}

}
