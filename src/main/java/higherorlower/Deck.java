package higherorlower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tools.XMLDomProcess;

/**
 * Az alkalmazásban használt kártyapaklit reprezentáló osztály.
 * Egy pakli 52 kártylapbóláll, melyet a {@link higherorlower.Card} osztály valósít meg.
 * A kártylapok értékét és elérését egy xml dokumentum tartalmazza.
 */

public class Deck {
	
	/**
	 * Az osztályban használt logger.
	 * @see <a href="http://www.slf4j.org/api/org/slf4j/Logger.html">SLF4J API</a>
	 */
	private static Logger logger = LoggerFactory.getLogger(Deck.class);
	
	/**
	 * Egy paklit reprezentáló lista.
	 */
	private static List<Card> d = new ArrayList<Card>();
	
	/**
	 * A kártypakli létrhozásához szükséges konstruktor, 
	 * amely tartalamzza a {@link #init} és {@link #shuffle} metódushívásokat.
	 */
	public Deck() {
		logger.info("Create new deck!");
		init();
		shuffle();
	}

	/**
	 * Metódus, amely a kártylapokat beolvassa az XML fájlból,
	 * ami a {@link tools.XMLDomProcess#process} metódust hívja meg.
	 */
	public void init() {
		d = XMLDomProcess.process("/Cards.xml");
		logger.info("Init a deck from an XML file.");
	}
	
	/**
	 * A kártyapaklit reprezentáló lista {@link #d} megkeverésére szolgáló metódus.
	 * @see java.util.Collections#shuffle(List)
	 */
	public void shuffle() {
		Collections.shuffle(d);	
		logger.info("Shuffling deck.");
	}
	
	/**
	 * Egy kártyalap kihúzása a kártyapakliból.
	 * @return a kih�zott k�rtyalap
	 */
	public Card deal() {
		Card c = d.get(0);
		d.remove(0);
		
		return c;
	}
	
	/**
	 * Visszaadja a kártyapaklit.
	 * @return a kártyapakli
	 */
	public static List<Card> getD() {
		return d;
	}

}
