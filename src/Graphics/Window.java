package Graphics;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/** Klasa rozszerzaj¹ca klasê JFrame, tworz¹ca okienko, które pe³ni umo¿liwia interakcjê u¿ytkownika z gr¹.
 *  @author Hubert Go³aszewski
 */
public class Window extends JFrame{

	/** Referencja na obiektu typu ActionPanel */
	public static ActionPanel actionPanel;
	private static final long serialVersionUID = 1L;

	/** Konstruktor, który determinuje wszystkie parametry okna tj. tytu³ okna, rozmiar, lokalizacja, roszerzalnoœæ, ikonê, layout oraz do³¹cza utworzony panel.
	 *  @param title - Tytu³ okna.
	 */
	public Window(String title){

		super(title);
		setSize(800, 600);
		setLocation(600, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Resources/Icon.png"));
		setLayout(new BorderLayout());
		actionPanel = new ActionPanel();
		add(actionPanel);
		pack();
		setVisible(true);

	}

}

