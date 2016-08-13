package GamePlay;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/** Klasa, która reprezentuje bonus (obiekt typu Rocket).
 * @author Hubert
 */
public class Rocket{

	/** Grafika reprezentuj¹ca bonus. */
	public static BufferedImage bonus;
	/** Zmienna wskazuj¹ca pozycjê przeszkody w poziomie. */ 
	private static int xPosition;
	/** Zmienna wskazuj¹ca pozycjê przeszkody w pionie. */ 
	private static int yPosition;
	/** Referencja na obiekty typu Random. */
	private static Random randPosition;

	/** Konstruktor klasy, który ustawia pocz¹tkowe pozycje bonusa (pozycja w pionie jest pseudolosowa) oraz wczytuje grafikê bonusa. */
	public Rocket(){

		randPosition = new Random();
		xPosition = 1200;
		yPosition = randPosition.nextInt(300) + 120;

		File imageFile = new File("Resources/Bonus.png");

		try{

			bonus = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B³¹d odczytu bonusa!");
			e.printStackTrace();

		}

	}

	/** Metoda zwracaj¹ca pozycjê bonusa w poziomie. 
	 *  @return Zwraca akutaln¹ pozycjê bonusa w poziomie.
	 */
	public int getX(){

		return this.xPosition;

	}

	/** Metoda zwracaj¹ca pozycjê bonusa w pionie. 
	 *  @return Zwraca akutaln¹ pozycjê bonusa w pionie.
	 */
	public int getY(){

		return this.yPosition;

	}

	/** Metoda ustawiaj¹ca pozycjê bonusa w pionie. 
	 *  @param y - Wartoœæ na jak¹ ma zostaæ ustawiona pozycja bonusa w pionie.
	 */
	public void setY(int y){

		this.yPosition = y;

	}

	/** Metoda ustawiaj¹ca pozycjê bonusa w poziomie. 
	 *  @param x - Wartoœæ na jak¹ ma zostaæ ustawiona pozycja bonusa w poziomie.
	 */
	public void setX(int x){

		this.xPosition = x;

	}

	/** Metoda zmniejszaj¹ca pozycjê bonusa w poziomie. 
	 *  @param x - Wartoœæ o jak¹ ma zostaæ zmniejszona pozycja bonusa w poziomie.
	 */
	public void setXDown(int x){

		this.xPosition -= x;
	}

}
