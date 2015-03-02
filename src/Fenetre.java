import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
	
	private static Fenetre f;
  private Panneau pan = new Panneau();
  
  public static final int HAUTEURFENETRE = 1080/2;
  public static final int LARGEURFENETRE = 1920/2;
  
  private static final int PAS = 50;
  private boolean jeuFini = false;
  private int cptBriques = pan.NB_BRIQUES;
  
  private boolean pause = false;
  
  // Les coordonnées de départ de notre rond
  private int x = pan.getPosX(), y = pan.getPosY();
  // Le booléen pour savoir si l'on recule ou non sur l'axe x
  private boolean backX = false;
  // Le booléen pour savoir si l'on recule ou non sur l'axe y
  private boolean backY = false;
  private int cpt;
  
  public Fenetre() {
    this.setTitle("Casse brique");
    this.setSize(LARGEURFENETRE, HAUTEURFENETRE);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setContentPane(pan);
    this.setVisible(true);
    this.addKeyListener(new ClavierListener());
  }
  
  class ClavierListener implements KeyListener{

	    public void keyPressed(KeyEvent event) {
	      System.out.println("Code touche pressée : " + event.getKeyCode() + " - caractère touche pressée : " + event.getKeyChar());
	      
	      //fleche gauche
	      if(37==event.getKeyCode() && pan.raqX > 0){
	    	  if(pan.raqX - PAS < 0){
	    		  pan.raqX = 0;
	    	  }
	    	  else{
	    		pan.raqX-=PAS;
	    	  }
	    		//System.out.println("a gauche");
	    	}
	      //fleche droite
	    	if(39==event.getKeyCode() &&  pan.raqX + pan.LARGEURRAQUETTE < pan.getWidth()){
	    		if(pan.raqX + pan.LARGEURRAQUETTE + PAS > pan.getWidth()){
	    			pan.raqX = pan.getWidth() - pan.LARGEURRAQUETTE;
	    		}
	    		else{
	    		pan.raqX+=PAS;
	    		}
	    		//System.out.println("a droite");
	    	}
	    	//caractere p
	    	if(80==event.getKeyCode()){
	    		pause = !pause;
	    		System.out.println("C'EST LA PAUSE!\n ");
	    	}
	    	
	    }

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	    	//System.out.println("déja bougé");
	    }

	    public void keyReleased(KeyEvent event) {
	      //System.out.println("Code touche relachée : " + event.getKeyCode() + " - caractère touche relachée : " + event.getKeyChar());         
	                    
	    }

	    public void keyTyped(KeyEvent event) {
	      //System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
	    
	    }   	
  
  private void collision()
  {
  	//La balle et les bords du panel
  	if(x < 0){
  		backX = false;
  	}
  	if(x > pan.getWidth() - pan.TAILLEBALLE){
  		backX = true;
  	}
  	if(y<0){
  		backY = false;
  	}
  	if(y > pan.getHeight() - pan.TAILLEBALLE && x>pan.raqX && x<pan.raqX + pan.LARGEURRAQUETTE){
  		backY = true;
  	}
  	
  	if(y > pan.getHeight() - pan.TAILLEBALLE && (x<pan.raqX || x>pan.raqX + pan.LARGEURRAQUETTE)){
  		pan.Vie --;
  		if(pan.Vie <= 0){
  			jeuFini = true;
  		}
  		backX = !backX;
  		backY = !backY;
  	}

	//Gère les colisions de la balle avec les briques (si la balle touche la brique, on "vire" la brique du champ de vision du joueur
	for(cpt=0; cpt<pan.NB_BRIQUES; cpt ++){
		if(x  > pan.tabX[cpt] && x < pan.tabX[cpt]+pan.LARGEURBRIQUE && y > pan.tabY[cpt] && y  < pan.tabY[cpt] + pan.HAUTEURBRIQUE){
			//backX = !backX;
			backY = !backY;
			pan.tabX[cpt] = -100;
			pan.tabY[cpt] = -100;
		}
	
	}

	  
  }

  private void go() {
    
	 pause = true;
	 backY = true;
	  
    while(!jeuFini){
    	
    	collision();
    	
    	if(!pause){
    		// Si on avance, on incrémente la coordonnée
    	      if (!backX)
    	        pan.setPosX(x++);
    	      // Sinon, on décrémente
    	      else
    	        pan.setPosX(x--);
    	      // Idem pour l'axe Y
    	      if (!backY)
    	        pan.setPosY(y++);
    	      else
    	        pan.setPosY(y--);
    	      // On redessine notre Panneau
    	      pan.repaint();
    	}
    	try{
      Thread.sleep(3);
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}
    }
    if(cptBriques == 0){
		  System.out.println("Gagné!");
	  }
	  else{
		  System.out.println("Perdu!");
	  }
  }
  
  public static void main(String[] args) {
	    f = new Fenetre();
	    f.go();
	  }
  
  }