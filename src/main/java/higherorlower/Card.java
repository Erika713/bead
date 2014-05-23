package higherorlower;

import javax.swing.ImageIcon;

/**
 * 
 * Az alkalmazásban használ kártyalapokat reprezentáló osztály.
 *
 */
public class Card {

	/**
	 * A kártylap értéke.
	 */
	private int value;
	
	/**
	 * A kártylaphoz tartozó kép.
	 */
	private ImageIcon picture;

	/**
	 * Konstruktor egy kártylap előállításához.
	 * @param value a kártyalap értéke
	 * @param picture a kártyalaphoz tartozó kép
	 */
	public Card(int value, ImageIcon picture) {
		this.value = value;
		this.picture = picture;
	}

	/**
	 * A kártylap értékét adja vissza.
	 * @return a kártylap értéke
	 */
	public int getValue() {
		return value;
	}

	/**
	 * A kártylap értékét űllítja be.
	 * @param value a kártylap értéke
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * A  kártyalaphoz tartozó képet adja vissza.
	 * @return a kártyalaphoz tartozó kép
	 */
	public ImageIcon getPicture() {
		return picture;
	}

	/**
	 * A kártyalaphoz tartozó képet állítja be.
	 * @param picture a kártyalaphoz tartozó kép 
	 */
	public void setPicture(ImageIcon picture) {
		this.picture = picture;
	}

}
