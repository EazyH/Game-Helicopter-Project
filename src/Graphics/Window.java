package Graphics;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/** Klasa rozszerzaj�ca klas� JFrame, tworz�ca okienko, kt�re pe�ni umo�liwia interakcj� u�ytkownika z gr�.
 *  @author Hubert Go�aszewski
 */
public class Window extends JFrame{

	/** Referencja na obiektu typu ActionPanel */
	public static ActionPanel actionPanel;
	private static final long serialVersionUID = 1L;

	/** Konstruktor, kt�ry determinuje wszystkie parametry okna tj. tytu� okna, rozmiar, lokalizacja, roszerzalno��, ikon�, layout oraz do��cza utworzony panel.
	 *  @param title - Tytu� okna.
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

