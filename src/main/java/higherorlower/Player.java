package higherorlower;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Az alkalmazást használó játékosokat reprezentáló osztály.
 *
 */

public class Player {
	
	/**
	 * Az osztályban használt logger.
	 * @see <a href="http://www.slf4j.org/api/org/slf4j/Logger.html">SLF4J API</a>
	 */
	private static Logger logger = LoggerFactory.getLogger(Player.class);

	/**
	 * A játékos neve.
	 */
	private String name;

	/**
	 * A játékos számára elérhető pénzösszeg.
	 */
	private double cash;

	
	/**
	 * A játékos aktuális kártyája.
	 */
	private Card currentCard;
	
	/**
	 * Konstruktor a játékos létrehozásához.
	 * @param name a játékos neve
	 */
	public Player(String name) {
		super();
		this.name = name;
		this.cash = 10000;
		
		logger.info("Create a new player.");
	}

	/**Visszaadja a játékos nevét.
	 * @return a játékos neve
	 */
	public String getName() {
		return name;
	}

	/**Beállítja a játékos nevét.
	 * @param name a játékos neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Visszaadja a játékos számára elérhető pénzösszeget.
	 * @return a játékos számára elárhető pénzösszeg
	 */
	public double getCash() {
		return cash;
	}
	
	
	/**
	 * Beállítja a kiosztott kártyalapot a játékos aktuális kártyájának.
	 * @param c A kiosztott kártyalap
	 */
	public void hitCard(Card c) {
		currentCard = c;
	}
	
	/**
	 * Visszaadja a játékos aktuális kártyáját.
	 * @return aktuális kártya
	 */
	public Card getCurCard() {
		return currentCard;
	}

	/**
	 * Beállítja a játékos számára elérhető pénzösszeget.
	 * @param cash a pénzösszeg
	 */
	public void setCash(double cash) {
		if (cash > 0) {
			this.cash = cash;
			logger.info("Set {} cash value to {}.", cash, this.getName());

		} else {
			this.cash = 0;
			logger.warn("Set null to cash.");
		}
	}

}
