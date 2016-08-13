package Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Action.Flying;
import Action.KeyListening;
import Action.MouseListening;
import Action.Stop;
import GamePlay.Helicopter;
import GamePlay.Map;
import GamePlay.Obstackle;
import GamePlay.Rocket;


/** Klasa rozszerzaj�ca klas� JPanel, kt�ra jest odpowiedzialna za panel menu, ��czenie element�w graficznych w jeden obraz i tworzenie odpowiednich animacji.
 *  Decyduje o przyznawanych punktach, wy�wietlaniu odpowiednich obraz�w zale�nie od aktualnego stanu mapy i helikoptera.
 *  Generuje r�wnie� pseudolosowe liczby jako pozycje przeszk�d i bonus�w.
 *  Dzi�ki niej, gracz jest w stanie interpretowa� widziany obraz oraz podejmowa� odpowiednie akcje.
 * @author Hubert
 */

public class ActionPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	/** Pr�g punktowy do poziomu 2. */
	private static final int level2Score = 6;
	/** Pr�g punktowy do poziomu 3. */
	private static final int level3Score = 12;
	/** Ilo�� punkt�w bonusowych. */
	private static final int bonusScore = 3;
	/** Grafika menu. */
	private static BufferedImage menuImage;
	/** Ikona przycisku start. */
	private static ImageIcon buttonStartIcon;
	/** Ikona przycisku exit. */
	private static ImageIcon buttonExitIcon;
	/** Przycisk startu. */
	private static JButton startButton;
	/** Przycisk wyj�cia. */
	private static JButton exitButton;
	/** Etykieta zdobytych punkt�w. */
	private static JLabel score;
	/** Etykieta bie�acego levelu. */
	private static JLabel level;
	/** Referencja na obiekt typu Flying. */
	private static Flying flying;
	/** Referencja na obiekt typu Helicopter. */
	private static Helicopter helicopter;
	/** Referencja na obiekt typu Obstackle. */
	private static Obstackle obstackle;
	/** Referencja na obiekt typu Map. */
	private static Map map;
	/** W�tek odpowiedzialny na ruch przeszk�d, bonus�w. */
	private static Thread threadFly;
	/** Listener myszki. */
	private static MouseListening mouseListen;
	/** Generator liczb pseudolosowych. */
	private static Random randomPosition;
	/** Referencja na obiekt typu Rocket. */
	private static Rocket rocket;
	/** Zmienna pomocnicza odpowiadaj�ca za losow� generacj� bonus�w. */
	private static int n;
	// Zmienna dodana przy oddawaniu projektu
	private static KeyListening KeyListen;
	
	/** Konstruktor klasy, kt�ry dodaje przyciski do panelu, ustawia t�o menu, dodaje listenery dla przycisk�w, listener dla myszki oraz etykiety punkt�w i poziomu. */
	public ActionPanel() {

		this.setLayout(null);

		// Stworzenie przycisku startu
		startButton = new JButton();
		startButton.setSize(150, 100);
		startButton.setLocation(120, 200);
		buttonStartIcon = new ImageIcon("Resources/startIcon.png");
		startButton.setIcon(buttonStartIcon);
		this.add(startButton);

		// Stworzenie przycisku wyj�cia
		exitButton = new JButton();
		exitButton.setSize(150, 100);
		exitButton.setLocation(120, 350);
		buttonExitIcon = new ImageIcon("Resources/exitIcon.png");
		exitButton.setIcon(buttonExitIcon);
		this.add(exitButton);

		// Dodanie reakcji na klikni�cie przycisku wyj�cia
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				System.exit(1);

			}

		});

		// Dodanie reakcji na klikni�cie przycisku startu
		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// Usuni�cie z panelu przycisk�w startu i wyj�cia
				remove(startButton);
				remove(exitButton);

				// Stworzenie obiektu generuj�cego pseudolosowe liczby
				randomPosition = new Random();

				// Stworzenie mapy, helikoptera, przeskody i bonusa
				map = new Map();
				helicopter = new Helicopter();
				obstackle = new Obstackle();
				rocket = new Rocket();

				// Dodanie reakcji na wydarzenia zwi�zane z myszk�
				mouseListen = new MouseListening(helicopter);
				addMouseListener(mouseListen);

				// Dodane podczas oddawania projektu
				KeyListen = new KeyListening();
				addKeyListener(KeyListen);
				requestFocus();
				
				// Stworzenie i uruchomienie nowego w�tku odpowiedzialnego za utrzymanie helikoptera w ruchu
				flying = new Flying(helicopter, obstackle, rocket, map);
				threadFly = new Thread(flying);
				threadFly.start();

				// Stworzenie i dodanie etykiety pokazuj�cej zdobyte punkty
				score = new JLabel();
				score.setSize(300, 50);
				score.setFont(new Font("Serif", Font.BOLD, 45));
				score.setForeground(Color.yellow.brighter());
				add(score);
				score.setLocation(45, 532);
				score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

				// Stworzenie i dodanie etykiety pokazuj�cej aktualny poziom mapy
				level = new JLabel();
				level.setSize(300, 50);
				level.setFont(new Font("Serif", Font.BOLD, 45));
				level.setForeground(Color.yellow.brighter());
				add(level);
				level.setLocation(575, 532);
				level.setText("LEVEL: 1");

				// Przemalowanie panelu (rozpocz�cie rozgrywki poniewa� zmienna map.level zosta�a zwi�kszona)
				map.setLevel(1);
				repaint();
				
				Stop stop = new Stop(threadFly);

			}

		});

		File imageFile = new File("Resources/Background.png");

		try{

			menuImage = ImageIO.read(imageFile);

		}	
		catch(IOException e){

			System.err.println("B��d odczytu t�a menu!");
			e.printStackTrace();

		}

		// Ustawienie rozmiaru panelu
		Dimension dimension = new Dimension(menuImage.getWidth(), menuImage.getHeight());
		setPreferredSize(dimension);

	}

	/** Metoda odpowiedzialna za zatrzymanie w�tku odpowiadaj�cego za ruch przeszk�d, helikoptera, bonus�w po wykryciu kolizji i przywr�cenie menu. */
	private void stopGame(){

		// Zatrzymanie w�tku odpowiadaj�cego za ruch helikoptera, przeszk�d i bonus�w
		threadFly.suspend();
		threadFly.stop(); 

		// Usuni�cie z panelu etykiet punkt�w, poziomu oraz listenera myszki
		removeMouseListener(mouseListen);
		remove(score);
		remove(level);

		// Dodanie do panelu utworzonych wcze�niej przycisk�w startu i wyj�cia
		add(startButton);
		add(exitButton); 

	}

	@SuppressWarnings("deprecation")

	/** Metoda nadpisana, odpowiadaj�ca za ca�� grafik� w grze (menu, animacje).
	 *  Wykrywa kolizje helikoptera, zatrzymuje animacj� w razie kolizji, zmienia stany mapy i helikoptera.
	 *  Odpowiada, za rysowanie bonus�w i przeszk�d w losowym miejscu na mapie. 
	 */
	public void paintComponent(Graphics g) {

		switch(map.level){ // Tutaj zamiast map.level powinna byc metoda map.getLevel(), ale gra si� wtedy wysypywa�a. Nie chcia�o mi si� ju� szuka� przyczyny.

		// Menu
		case 0:

			// Rysowanie menu
			g.drawImage(menuImage, 0, 0, this);
			break;

		// Poziom 1	
		case 1:

			// W przypadku wykrycia jakiejkolwiek kolizji (z g�r�, do�em, klockiem)
			if(helicopter.getCrashState() == false && ((helicopter.getY() < 87 || helicopter.getY() > 465) || 
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 33) && (helicopter.getY() > obstackle.getY() && helicopter.getY() < obstackle.getY() + 162)) ||
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 33) && (helicopter.getY() + 44 > obstackle.getY() && helicopter.getY() + 44 < obstackle.getY() + 162)) ||
					((helicopter.getX() > obstackle.getX() && helicopter.getX() < obstackle.getX() + 33) && (helicopter.getY() + 22 > obstackle.getY() && helicopter.getY() + 22 < obstackle.getY() + 162)))) {


				helicopter.setState("crash");

				// Je�eli kolizja by�a z do�em (bez spadania) nie b�dzie animacji spadania po rozbiciu
				if(helicopter.getY() > 465) {

					// Zako�czenie gry i wr�cenie do menu
					stopGame();
					g.drawImage(menuImage, 0, 0, this);
					break;

				}

			}
			// W przypadku gdy kolizja zosta�a ju� wykryta i nie by�a to kolizja z do�em (animacja spadania po rozbiciu) lub helikopter nie ma stanu rozbicia
			else{

				// W przypadku gdy helikopter jest w stanie rozbicia 
				if(helicopter.getCrashState() == true){

					// Utrzymanie odpowiedniego otoczenia (mapa, przeszkody) podczas gdy spada rozbity helikopter
					g.drawImage(map.map1, 0, 0, null);
					g.drawImage(obstackle.level1, obstackle.getX(), obstackle.getY(), null);
					g.drawImage(helicopter.helicopterCrashed, helicopter.getX(), helicopter.getY(), null);
					g.drawImage(map.gameOver, 150, 0, null);

					// Je�li helikopter osi�dzie na dolnej granicy
					if(helicopter.getY() > 465) {

						// Zako�czenie gry i wr�cenie do menu
						stopGame();
						g.drawImage(menuImage, 0, 0, this);
						break;

					}

				}
				// W przypadku gdy helikopter nie jest w stanie rozbicia
				else{

					// Je�li bonus jest aktywny i helikopter zgarnie bonus
					if(map.getBonusState() == true && (((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
							(helicopter.getY() >= rocket.getY() && helicopter.getY() <= rocket.getY() + 40)) ||
							((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
									helicopter.getY() + 44 >= rocket.getY() && helicopter.getY() + 44 <= rocket.getY() + 40))){

						// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji, dodanie punkt�w bonusowych i zmiana etykiety
						map.setBonus("OFF");
						rocket.setX(randomPosition.nextInt(500) + 1000);
						rocket.setY(randomPosition.nextInt(300) + 120);
						helicopter.addScore(bonusScore);
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					// Je�li helikopter si� wznosi
					if(helicopter.getFlyState() == true){

						g.drawImage(map.map1, 0, 0, null);
						g.drawImage(helicopter.helicopterAscending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true) {

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0) {

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}
					// Je�li helikopter opada
					else{

						g.drawImage(map.map1, 0, 0, null);
						g.drawImage(helicopter.helicopterDescending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true) {

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0) {

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}

					// Je�li zdobyte punkty s� wi�ksze lub r�wne limitowi na poziom 2
					if(helicopter.getScore() >= level2Score) {

						// Ustawienie poziomu bonusa na 2, zmiana etykiety i podniesienie flagi pierwszej zmiany mapy
						map.setLevel(2);
						level.setText("LEVEL: " + Integer.toString(map.getLevel()));
						map.setMapChange("ON");

					}

					// Je�li przeszkoda znikn�a z pola widzenia i jest to 1 poziom
					if(obstackle.getX() < 0 && map.getLevel() == 1) { 

						// Wygenerowanie nowej pozycji przeszkody, dodanie punkta, zmiana etykiety i pr�ba wygenerowania nowego bonusa
						obstackle.setStartingPosition(800, randomPosition.nextInt(220) + 115);
						helicopter.addScore(1); 
						n = randomPosition.nextInt(9) + 1;
						if(helicopter.getScore() % n == 0) map.setBonus("ON");
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					g.drawImage(obstackle.level1, obstackle.getX(), obstackle.getY(), null);

				} 

			}

			break;

		// Poziom 2	
		case 2:

			// W przypadku gdy flaga zmiany mapy jest podniesiona
			if(map.getMapChange() == true){

				// W przypadku wykrycia jakiejkolwiek kolizji (z g�r�, do�em, klockiem) i gdy wcze�niej nie by�o kolizji
				if(helicopter.getCrashState() == false && ((helicopter.getY() < 87 || helicopter.getY() > 465) || 
						((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 33) && (helicopter.getY() > obstackle.getY() && helicopter.getY() < obstackle.getY() + 162)) ||
						((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 33) && (helicopter.getY() + 44 > obstackle.getY() && helicopter.getY() + 44 < obstackle.getY() + 162)) ||
						((helicopter.getX() > obstackle.getX() && helicopter.getX() < obstackle.getX() + 33) && (helicopter.getY() + 22 > obstackle.getY() && helicopter.getY() + 22 < obstackle.getY() + 162)))) {

					helicopter.setState("crash");

					// Je�eli kolizja by�a z do�em (bez spadania) nie b�dzie animacji spadania po rozbiciu
					if(helicopter.getY() > 465) {

						// Zako�czenie gry i wr�cenie do menu
						stopGame();
						g.drawImage(menuImage, 0, 0, this);
						break;

					}

				}

			}

			// Je�li flaga zmiany mapy jest opuszczona, nie by�o wcze�niej kolizji i zosta�a ona wykryta
			if(map.getMapChange() == false && (helicopter.getCrashState() == false && ((helicopter.getY() < 87 || helicopter.getY() > 465) || 
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 35) && (helicopter.getY() > obstackle.getY() && helicopter.getY() < obstackle.getY() + 206)) ||
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 35) && (helicopter.getY() + 44 > obstackle.getY() && helicopter.getY() + 44 < obstackle.getY() + 206)) ||
					((helicopter.getX() > obstackle.getX() && helicopter.getX() < obstackle.getX() + 35) && (helicopter.getY() + 22 > obstackle.getY() && helicopter.getY() + 22 < obstackle.getY() + 206))))) {

				helicopter.setState("crash");

				// Je�eli kolizja by�a z do�em (bez spadania) nie b�dzie animacji spadania po rozbiciu
				if(helicopter.getY() > 465) {

					// Zako�czenie gry i wr�cenie do menu
					stopGame();
					g.drawImage(menuImage, 0, 0, this);
					break;

				}

			}
			// W przypadku gdy helikopter jest ju� w stanie rozbicia, nie wykryto kolizji lub flaga zmiany mapy jest podniesiona
			else{

				// Je�li helikopter jest rozbity
				if(helicopter.getCrashState() == true){

					g.drawImage(map.map2, 0, 0, null);

					// Je�li flaga zmiany mapy jest podniesiona
					if(map.getMapChange() == true){

						g.drawImage(obstackle.level1, obstackle.getX(), obstackle.getY(), null);

					}
					// Je�li flaga zmiany mapy nie jest podniesiona
					else{

						g.drawImage(obstackle.level2, obstackle.getX(), obstackle.getY(), null);

					}

					g.drawImage(helicopter.helicopterCrashed, helicopter.getX(), helicopter.getY(), null);
					g.drawImage(map.gameOver, 150, 0, null);

					// Je�li helikopter osi�dzie na dolnej granicy
					if(helicopter.getY() > 465) {

						// Zako�czenie gry i wr�cenie do menu
						stopGame();
						g.drawImage(menuImage, 0, 0, this);
						break;

					}

				}
				// Je�li helikopter nie jest rozbity
				else{

					// Je�li bonus jest aktywny i helikopter zgarnie bonus
					if((map.getBonusState() == true && (((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
							(helicopter.getY() >= rocket.getY() && helicopter.getY() <= rocket.getY() + 40)) ||
							((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
									helicopter.getY() + 44 >= rocket.getY() && helicopter.getY() + 44 <= rocket.getY() + 40)))){

						// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji, dodanie punkt�w bonusowych i zmiana etykiety
						map.setBonus("OFF");
						rocket.setX(randomPosition.nextInt(500) + 1000);
						rocket.setY(randomPosition.nextInt(300) + 120);
						helicopter.addScore(bonusScore);
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					// Je�li helikopter si� wznosi
					if(helicopter.getFlyState() == true){

						g.drawImage(map.map2, 0, 0, null);
						g.drawImage(helicopter.helicopterAscending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true) {

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0) {

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}
					// Je�li helikopter opada
					else{

						g.drawImage(map.map2, 0, 0, null);
						g.drawImage(helicopter.helicopterDescending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true) {

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0) {

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}

					// Je�li zdobyte punkty s� wi�ksze lub r�wne limitowi na poziom 3
					if(helicopter.getScore() >= level3Score) {

						// Ustawienie poziomu bonusa na 3, zmiana etykiety i podniesienie flagi pierwszej zmiany mapy
						map.setLevel(3);
						level.setText("LEVEL: " + Integer.toString(map.getLevel()));
						map.setMapChange("ON");

					}

					// Je�li przeszkoda znikn�a z pola widzenia i jest to 2 poziom
					if(obstackle.getX() < 0 && map.getLevel() == 2) { 

						// Opuszczenie flagi zmiany mapy, wygenerowanie nowej pozycji przeszkody, dodanie punkta, zmiana etykiety, pr�ba wygenerowania nowego bonusa
						map.setMapChange("OFF");
						obstackle.setStartingPosition(800, randomPosition.nextInt(186) + 115);
						helicopter.addScore(1); 
						n = randomPosition.nextInt(9) + 1;
						if(helicopter.getScore() % n == 0) map.setBonus("ON");
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					// Je�li flaga zmiany mapy jest podniesiona 
					if(map.getMapChange() == true){

						g.drawImage(obstackle.level1, obstackle.getX(), obstackle.getY(), null);

					}
					// Je�li flaga zmiany mapy nie jest podniesiona 
					else{

						g.drawImage(obstackle.level2, obstackle.getX(), obstackle.getY(), null);

					}

				}  

			}

			break;

		// Poziom 3	
		case 3:

			// W przypadku gdy flaga zmiany mapy jest podniesiona
			if(map.getMapChange() == true){

				// W przypadku wykrycia jakiejkolwiek kolizji (z g�r�, do�em, klockiem) i gdy wcze�niej nie by�o kolizji
				if(helicopter.getCrashState() == false && ((helicopter.getY() < 87 || helicopter.getY() > 465) || 
						((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 35) && (helicopter.getY() > obstackle.getY() && helicopter.getY() < obstackle.getY() + 206)) ||
						((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 35) && (helicopter.getY() + 44 > obstackle.getY() && helicopter.getY() + 44 < obstackle.getY() + 206)) ||
						((helicopter.getX() > obstackle.getX() && helicopter.getX() < obstackle.getX() + 35) && (helicopter.getY() + 22 > obstackle.getY() && helicopter.getY() + 22 < obstackle.getY() + 206)))) {

					helicopter.setState("crash");

					// Je�eli kolizja by�a z do�em (bez spadania) nie b�dzie animacji spadania po rozbiciu
					if(helicopter.getY() > 465) {

						// Zako�czenie gry i wr�cenie do menu
						stopGame();
						g.drawImage(menuImage, 0, 0, this);
						break;

					}

				}

			}

			// Je�li flaga zmiany mapy jest opuszczona, nie by�o wcze�niej kolizji i zosta�a ona wykryta
			if(map.getMapChange() == false && (helicopter.getCrashState() == false && ((helicopter.getY() < 87 || helicopter.getY() >= 465) || 
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 37) && (helicopter.getY() > obstackle.getY() && helicopter.getY() < obstackle.getY() + 245)) ||
					((helicopter.getX() + 95 > obstackle.getX() && helicopter.getX() + 95 < obstackle.getX() + 37) && (helicopter.getY() + 44 > obstackle.getY() && helicopter.getY() + 44 < obstackle.getY() + 245)) ||
					((helicopter.getX() > obstackle.getX() && helicopter.getX() < obstackle.getX() + 37) && (helicopter.getY() + 22 > obstackle.getY() && helicopter.getY() + 22 < obstackle.getY() + 245))))) {

				helicopter.setState("crash");

				// Je�eli kolizja by�a z do�em (bez spadania) nie b�dzie animacji spadania po rozbiciu
				if(helicopter.getY() > 465) {

					// Zako�czenie gry i wr�cenie do menu
					stopGame();
					g.drawImage(menuImage, 0, 0, this);
					break;

				}

			}
			// W przypadku gdy helikopter jest ju� w stanie rozbicia, nie wykryto kolizji lub flaga zmiany mapy jest podniesiona
			else{

				// Je�li helikopter jest rozbity
				if(helicopter.getCrashState() == true){

					g.drawImage(map.map3, 0, 0, null);

					// Je�li flaga zmiany mapy jest podniesiona
					if(map.getMapChange() == true){

						g.drawImage(obstackle.level2, obstackle.getX(), obstackle.getY(), null);

					}
					// Je�li flaga zmiany mapy nie jest podniesiona
					else{

						g.drawImage(obstackle.level3, obstackle.getX(), obstackle.getY(), null);

					}

					g.drawImage(helicopter.helicopterCrashed, helicopter.getX(), helicopter.getY(), null);
					g.drawImage(map.gameOver, 150, 0, null);

					// Je�li helikopter osi�dzie na dolnej granicy
					if(helicopter.getY() > 465) {

						// Zako�czenie gry i wr�cenie do menu
						stopGame();
						g.drawImage(menuImage, 0, 0, this);
						break;

					}

				}
				// Je�li helikopter nie jest rozbity
				else{

					// Je�li bonus jest aktywny i helikopter zgarnie bonus
					if(map.getBonusState() == true && (((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
							(helicopter.getY() >= rocket.getY() && helicopter.getY() <= rocket.getY() + 40)) ||
							((helicopter.getX() + 95 >= rocket.getX() && helicopter.getX() + 95 <= rocket.getX() + 40) &&
									helicopter.getY() + 44 >= rocket.getY() && helicopter.getY() + 44 <= rocket.getY() + 40))){

						// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji, dodanie punkt�w bonusowych i zmiana etykiety
						map.setBonus("OFF");
						rocket.setX(randomPosition.nextInt(500) + 1000);
						rocket.setY(randomPosition.nextInt(300) + 120);
						helicopter.addScore(bonusScore);
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					// Je�li helikopter si� wznosi
					if(helicopter.getFlyState() == true){

						g.drawImage(map.map3, 0, 0, null);
						g.drawImage(helicopter.helicopterAscending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true){

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0){

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}
					// Je�li helikopter opada
					else{

						g.drawImage(map.map3, 0, 0, null);
						g.drawImage(helicopter.helicopterDescending, helicopter.getX(), helicopter.getY(), null);

						// Je�li bonus jest aktywny
						if(map.getBonusState() == true){

							// Je�li bonus zosta� pomini�ty przez helikopter
							if(rocket.getX() < 0){

								// Dezaktywacja bonusa, wygenerowanie nowej przysz�ej pozycji
								map.setBonus("OFF");
								rocket.setX(randomPosition.nextInt(500) + 1000);
								rocket.setY(randomPosition.nextInt(300) + 120);

							}
							// Je�li bonus jeszcze nie zosta� pomini�ty przez helikopter
							else{

								g.drawImage(rocket.bonus, rocket.getX(), rocket.getY(), null); 

							}

						}

					}

					// Je�li przeszkoda znikn�a z pola widzenia i jest to 3 poziom
					if(obstackle.getX() < 0 && map.getLevel() == 3){ 

						// Opuszczenie flagi zmiany mapy, wygenerowanie nowej pozycji przeszkody, dodanie punkta, zmiana etykiety, pr�ba wygenerowania nowego bonusa
						map.setMapChange("OFF");
						obstackle.setStartingPosition(800, randomPosition.nextInt(145) + 115);
						helicopter.addScore(1); 
						n = randomPosition.nextInt(1) + 9;
						if(helicopter.getScore() % n == 0) map.setBonus("ON");
						score.setText("SCORE: " + Integer.toString(helicopter.getScore()));

					}

					// Je�li flaga zmiany mapy jest podniesiona 
					if(map.getMapChange() == true){

						g.drawImage(obstackle.level2, obstackle.getX(), obstackle.getY(), null);

					}
					// Je�li flaga zmiany mapy nie jest podniesiona 
					else{

						g.drawImage(obstackle.level3, obstackle.getX(), obstackle.getY(), null);

					}

				}  

			}

			break;

		}

	}

}




