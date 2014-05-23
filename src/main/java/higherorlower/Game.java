package higherorlower;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A játék menetét és irányítását reprezentáló osztály.
 * */

public class Game {
	
	/**
	 * Az osztályban haszált logger.
	 * @see <a href="http://www.slf4j.org/api/org/slf4j/Logger.html">SLF4J API</a>
	 */
	private static Logger logger = LoggerFactory.getLogger(Game.class);

	/**
	 * A játékban résztvevő játékos, amit a {@link higherorlower.Player} osztály valósít meg.  
	 */
	private Player player1;
	
	/**
	 * A játékban használt kártyapakli, amit a {@link higherorlower.Deck} osztály valósít meg.
	 */
	private Deck d;
	
	/**
	 * Az asztalon lévő aktuális kárty, amit a {@link higherorlower.Card} osztály valósít meg meg.
	 * */
	private Card actualCard;
	
	/**
	 * A {@link #d} pakliban az {@link #actualCard} kártyánál nagyobb értékű kártyák számát tartalmazza.
	 * */
	private int higherCards;
	
	/**
	 * A {@link #d} pakliban az {@link #actualCard} kártyánál kisebb értékű kártyák számát tartalmazza.
	 * */
	private int lowerCards;
	
	/**
	 * A {@link #d} pakliból még ki nem húzott kártyák számát tartalmazza.
	 * */
	private int unusedCards;
	
	/**
	 * A változó <code>true</code> értékű, ha a tippünk helyes volt, egyébként <code>false</code>.
	 */
	private boolean win;
	
	/**
	 * A változó <code>true</code> értékű, ha két lap egyforma, egyébként <code>false</code>.
	 */
	private boolean equalCards = false;
	
	/**
	 * A változó <code>true</code> értékű, ha a játszmát végig játszottuk és nem veszítettünk, 
	 * egyébként <code>false</code>.
	 */
	private boolean finalWin = false;
	
	/**
	 * A változó <code>true</code> értékű, ha a játszma során 2-nél több rossz tipp volt 
	 * vagy elfogyott a pénz, egyébként <code>false</code>.
	 */
	private boolean gameOver = false;

	/**
	 * A játszmák számát nyívántartó egész.
	 */
	private static int roundNumber = 0;
	
	/**
	 * A helyes válaszok számát nyílvántartó egész.
	 */
	private int correctAnswers;
	
	/**
	 * A helytelen válaszok számát nyílvántartó egész.
	 */
	private int wrongAnswers;
	
	/**
	 * A a játszmák száma a győzelemig.
	 */
	static final int ROUNDTOWIN = 7;
	
	/**
	 * A maximális helytelen válszok száma.
	 */
	static final int WRONGANSWERSTOLOSE = 2;
	
	/**
	 * Konstruktor egy játék létrehozásához.
	 */
	public Game() {
		
		logger.info("Init a new game.");
		player1 = new Player("Player1");
		d = new Deck();
		win = false;
		correctAnswers = 0;
		wrongAnswers = 0;
		roundNumber= 0;
	}

	/**
	 * Inizializál egy új játékot.
	 */
	
	public void newGame() {
		logger.info("Init a new game.");
		d = new Deck();
		win = false;
		correctAnswers = 0;
		wrongAnswers = 0;
		roundNumber=0;
	}
	
	/**
	 * A játékost alapállásba helyező metódus.
	 * Új kártyapakli kerül létrehozásra. 
	 * A résztvevő játékos számára új lapok kerül kiosztásra, a játszmák száma eggyel nő.
	 */
	public void baseStand() {
		logger.info("Set game state to start.");
		actualCard = d.deal();
		player1.hitCard(d.deal());
		roundNumber = 1;
		
	}
	

	/**
	 * Új lapot oszt a játékosnak és az asztalon lévő kártya a játékos előző kártyája lesz.
	 */
	public void newRound() {
		logger.info("{} get a card.", this.getPlayer1().getName());
		win = false;
		actualCard = player1.getCurCard();
		player1.hitCard(d.deal());
		roundNumber++;
		
	}
	
	/**
	 * Ellenőrzi, hogy helyes volt e a tipp, ha a játékos a Lower gombot nyomta meg.
	 */
	public void checkLower() {
		equalCards = false;
		
		if(player1.getCurCard().getValue() < actualCard.getValue()){
			win = true;
			correctAnswers++;
			
			logger.info("Correct Answer.");
		}
		else if (player1.getCurCard().getValue() == actualCard.getValue())
		{
			equalCards = true;
			roundNumber--;
			logger.info("Equal Cards.");
		}
		else
		{
			win = false;
			wrongAnswers++;
			logger.info("Wrong Answer.");
		}
	}
	
	/**
	 * Ellenőrzi, hogy helyes volt e a tipp, ha a játékos a Lower gombot nyomta meg.
	 */
	public void checkHigher() {
		equalCards = false;
		
		if(player1.getCurCard().getValue() > actualCard.getValue()){
			win = true;
			correctAnswers++;
			
			logger.info("Correct Answer.");
		}
		else if (player1.getCurCard().getValue() == actualCard.getValue()){
			equalCards = true;
			roundNumber--;
			logger.info("Equal Cards.");
		}
		else{
			win = false;
			wrongAnswers++;
			logger.info("Wrong Answer.");
		}
	}


	/**
	 * Ha helyesen tippelt a játékos, akkor az aktuális pénzösszegéhez,
	 * a tét kétszeresét hozzáadjuk.
	 * @param money a játékos átal megjátszott tét
	 */
	public void playerWin(double money) {
		player1.setCash(player1.getCash() + money * 2);
	}

	/**
	 * Ha helytelenül tippelt a játékos, akkor az aktuális pénzösszegéből,
	 * a tét kétszeresét kivonjuk.
	 * @param money a játékos átal megjátszott tét
	 */
	public void playerLose(double money) {
		player1.setCash(player1.getCash() - money * 2);
	}
	
	/**
	 * Ellenőrizzük, hogy végetért e a játék.
	 */
	public void checkEnd() {
		
		finalWin  = false;
		gameOver = false;
		
		if(roundNumber == ROUNDTOWIN && wrongAnswers <= 2) 
		{
			finalWin =  true;
			logger.info("{} won.", this.getPlayer1().getName());
		}
		
		else
		{
			if (player1.getCash() == 0 ) {
				gameOver = true;
				logger.info("Game end. Not enough money!");
			}
			else if(wrongAnswers > 2 ){
				gameOver = true;
				logger.info("Game end. Too much wrong answer!");
			}
		}
		
	}
	
	/**Visszaadja a játékost.
	 * @return játékos
	 */
	public Player getPlayer1() {
		return player1;
	}

	/**
	 * Visszaadja a játszmák számát.
	 * @return játszmák száma
	 */
	public int getRoundNumber(){
		return roundNumber;
	}
	
	/**
	 * Visszaadja a tipp helyességét.
	 * @return tipp helyessége
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * Visszadja a helyes válaszok számát.
	 * @return helyes válaszok száma
	 */
	public  int getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * Visszaadja a helytelen tippek számát.
	 * @return helytelen tippek száma
	 */
	public  int getWrongAnswers() {
		return wrongAnswers;
	}

	/**
	 * Visszaadja az aktuális kártyát.
	 * @return aktuális kártya
	 */
	public Card getActualCard() {
		return actualCard;
	}
	
	/**
	 *A játékos számára ad egy tippet. Megszámolja, 
	 *hogy az aktuális káártyánál hány nagyobb, 
	 *kisebb kárty van illetve, hogy mennyi, 
	 *amit még nem húztak ki.
	 */
	public void getHint()
	{
		higherCards =0;
		lowerCards = 0;
		unusedCards = 0;
		
		int c;
		int a;
		
		List<Card> d = new ArrayList<Card>();
		d = Deck.getD();
		
		for (int i = 0; i < d.size(); i++) 
		{		
			c = d.get(i).getValue();
			a = actualCard.getValue();
			
			if(c > a){
				higherCards++;
			}
			else if (c < a){
				lowerCards++;
			}
		}
		
		unusedCards = d.size();
	}


	/**
	 * Visszadja a pakliban az aktuális kártyánál nagyobb kártyák számát.
	 * @return nagyobb kártyák száma
	 */
	public int getHigherCards() {
		return higherCards;
	}

	/**
	 * Visszadja a pakliban az aktuális kártyánál kisebb kártyák számát.
	 * @return kisebb kártyák száma
	 */
	public int getLowerCards() {
		return lowerCards;
	}

	/**
	 * Visszadja a pakliban a még ki nem húzott kártyák számát.
	 * @return ki nem húzott kártyák száma
	 */
	public int getUnusedCards() {
		return unusedCards;
	}

	/**
	 * Egyező értékű kártyák esetén <code>true</code>, egyébként <code>false</code> értékű.
	 * @return egyező kártyák voltak e
	 */
	public boolean isEqualCards() {
		return equalCards;
	}

	/**
	 * Ha játék győzelemmel ért véget <code>true</code>, egyébként <code>false</code>.
	 * @return győzött e a játékos
	 */
	public boolean isFinalWin() {
		return finalWin;
	}

	/**
	 * Ha a játékos vesztett <code>true</code> értékkel tér vissze, egyénként <code>false</code>.
	 * @return vesztett e a játékos
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	
	
	
}
