import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	private int posX = Fenetre.LARGEURFENETRE / 2;
	private int posY = Fenetre.HAUTEURFENETRE / 3  * 2;

	protected int raqX;
	protected int raqY;

	protected int tabX[] = new int[NB_BRIQUES];
	protected int tabY[] = new int[NB_BRIQUES];

	protected static final int HAUTEURRAQUETTE = 10;
	protected static final int LARGEURRAQUETTE = Fenetre.LARGEURFENETRE / 10;
	protected static final int TAILLEBALLE = 20;
	protected static final int HAUTEURBRIQUE = 40;
	protected static final int LARGEURBRIQUE = 80;
	protected static final int NB_BRIQUES = 27;
	private static final int XESPACE_BRIQUES = 100;
	private static final int YESPACE_BRIQUES = 60;

	private static boolean uneFois = true;
	
	protected static int Vie = 3;

	public void paintComponent(Graphics g) {
		// Pour le fond
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Pour la balle
		g.setColor(Color.red);
		g.fillOval(posX, posY, TAILLEBALLE, TAILLEBALLE);

		// Pour la raquette
		g.setColor(Color.blue);
		if (uneFois){
			raqX = this.getWidth() / 2;
			raqY = this.getHeight() - 10;
		}
		g.fillRect(raqX, raqY, LARGEURRAQUETTE, HAUTEURRAQUETTE);

		// Pour les briques

		g.setColor(Color.orange);
		
		int cpt = 0;
		int x = 0;
		int y = 0;
		
		if(uneFois){
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < NB_BRIQUES / 3; j++) {
					tabX[cpt] = x;
					tabY[cpt] = y;
					g.fillRect(tabX[cpt], tabY[cpt], LARGEURBRIQUE, HAUTEURBRIQUE);
					x += XESPACE_BRIQUES;
					cpt++;
				}
				x = 0;
				y += YESPACE_BRIQUES;
			}
		}
		else{
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < NB_BRIQUES / 3; j++) {
				g.fillRect(tabX[cpt], tabY[cpt], LARGEURBRIQUE, HAUTEURBRIQUE);
				x += XESPACE_BRIQUES;
				cpt++;
			}
			x = 0;
			y += YESPACE_BRIQUES;
		}
		}
		
	
		g.setColor(Color.black);
	g.drawString("Vie(s) : " + Vie , this.getWidth() / 2, this.getHeight() / 3 * 2);
	
	uneFois = false;
	}

	// Pour le rond
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	// Pour la raquette
	public void setRaqX(int raqX) {
		this.raqX = raqX;
		repaint();
	}

	public int getRaqX() {
		return raqX;
	}

	public void setRaqY(int raqY) {
		this.raqY = raqY;
		repaint();
	}

	public int getRaqY() {
		return raqY;
	}

}