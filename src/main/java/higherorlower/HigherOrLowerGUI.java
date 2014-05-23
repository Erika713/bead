package higherorlower;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tools.DBUtil;

/**
 * Az alkalmazás grafikus felületét megvalósító osztály.
 */
public class HigherOrLowerGUI extends JFrame {
	
	/**
	 * Az osztályban használt logger.
	 * @see <a href="http://www.slf4j.org/api/org/slf4j/Logger.html">SLF4J API</a>
	 */
	private static Logger logger = LoggerFactory.getLogger(HigherOrLowerGUI.class);

	/**
	 * A felület egy komponense.
	 */
	protected static final Component HigherOrLowerGUI = null;
	
	/**
	 * Egy játék, melyet a {@link higherorlower.Game} osztály valósít meg.
	 */
	private static Game g;
	
	/**
	 * A megjátszott tét.
	 */
	double betmoney;
	
	/**
	 * A felület contentpane-je.
	 */
	private JPanel contentPane;
	
	/**
	 * A tét megadásához szükséges textField.
	 * */
	private JTextField textField_Bet;
	
	/**
	 * A felület egy label-je, ami "Bet:" feliratot jeleníti meg.
	 */
	private JLabel lblBet;
	/**
	  *  A felület egy label-je, ami a játékos tétjét jeleníti meg.
	 */
	private JLabel lblBetLabel ;
	
	/**
	 * A felület egy label-je, ami "Player's name:" feliratot jeleníti meg.
	 */
	private JLabel lblPlayersName;
	
	/**
	  *  A felület egy label-je, ami a játékos nevét jeleníti meg.
	 */
	private JLabel labelPlayerName2;
	
	/**
	 * A felület egy label-je, ami "Player's cash:" feliratot jeleníti meg.
	 */
	private JLabel lblPlayersCash;
	
	/**
	  *  A felület egy label-je, ami a játékos aktuális pénzösszegét jeleníti meg.
	 */
	private JLabel labelPlayercash2;
	
	/**
	 * A felület egy label-je, ami "Correct Answers:" feliratot jeleníti meg.
	 */
	private JLabel lblCorrectAnswers ;
	
	/**
	  *  A felület egy label-je, ami a játékos helyes válaszainak számát jeleníti meg.
	 */
	private JLabel labelCorrectAnswers;
		
	/**
	 * A felület egy label-je, ami "Wrongs Answers:" feliratot jeleníti meg.
	 */
	private JLabel lblWrongAnswers;
	 
	/**
	  *  A felület egy label-je, ami a játékos helytelen válaszainak számát jeleníti meg.
	 */
	private JLabel labelWrongAnwers ;
	
	/**
	 * A felület kártyapakli panel-je.
	 */
	private JPanel deckpanel;
	
	/**
	 * A felület játékos panel-je.
	 */
	private JPanel playerpanel;
	
	/**
	 * A felület tipp panel-je.
	 */
	private JPanel panelHint;
	
	/**
	 * A Hint panel egy label-je, ami a "A Hint:" feliratot jeleníti meg.
	 */
	private JLabel lblAHint;
	
	/**
	 * A Hint panel egy label-je, ami a "Lower Cards:" feliratot jeleníti meg.
	 */
	private JLabel lblLowerCards;
	
	/**
	  *  A Hint panel egy label-je, ami a pakliban lévő, azktuális kártyától 
	  *  kisebb értékű kártyát számát jeleníti meg és azt hogy ez hány százaléka 
	  *  a ki nem húzott kártyáknak.
	 */
	private JLabel lblLowerCards2;
	
	/**
	 * A Hint panel egy label-je, ami a "Unused Cards:" feliratot jeleníti meg.
	 */
	private JLabel lblUnusedCard;
	
	/**
	  *  A Hint panel egy label-je, ami a pakliból ki nem húzott kártyáknak a számát jeleníti meg.
	 */
	private JLabel lblUnusedCard2;
	
	/**
	 * A Hint panel egy label-je, ami a "Higher Cards:" feliratot jeleníti meg.
	 */
	private JLabel lblHigherCards;
	
	/**
	  *  A Hint panel egy label-je, ami a pakliban lévő, azktuális kártyától 
	  *  nagyobb értékű kártyát számát jeleníti meg és azt hogy ez hány százaléka 
	  *  a ki nem húzott kártyáknak.
	 */
	private JLabel lblHigherCards2;
	
	/**
	 * A felület egy label-je, ami megjeleníti, hogy helyes volt-e a tipp vagy sem.
	 */
	private JLabel lblResult;
	
	/**
	 * A felület egy label-je, ami megjeleníti, hogy a játékos nyert vagy vesztett.
	 */
	private JLabel lblWinner;
	
	/**
	 * A felület Deal gombja.
	 */
	private JButton btnDeal;
	
	/**
	 * A felület Bet gombja.
	 */
	private JButton btnBet;
	
	/**
	 * A felület All In gombja.
	 */
	private JButton btnAllIn;
	
	/**
	 * A felület Higher gombja.
	 */
	private JButton btnHigher;
	
	/**
	 * A felület Lower gombja.
	 */
	private JButton btnLower;
	
	/**
	 * A felület New Game gombja.
	 */
	private JButton btnNewGame;
	
	/**
	 * A felület Hint gombja.
	 */
	private JButton btnHint;
	
	/**
	 * A felület Give Up gombja.
	 */
	private JButton btnGiveUp;
	
	/**
	 * A felület menübárja.
	 */
	private JMenuBar menuBar;
		
	/**
	 * A felület File menüje.
	 */
	private JMenu mnFile;
		
	 /**
	 * A felület File menüjének Save eleme.
	 */
	private JMenuItem mntmSave;
		
	 /**
	  * A felület File menüjének Help eleme.
	 */
	private JMenuItem mntmHelp;
		
	 /**
	 * A felület File menüjének Exit eleme.
	 */
	private JMenuItem mntmExit;
		
		
	/**
	 * Az alkalmazást indító <code>main</code> metódus.
	 * @param args parancssori argumentumok
	 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						g=new Game();					
						HigherOrLowerGUI frame = new HigherOrLowerGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Catch an error.");
						
					}
				}
			});
		}

	/**
	 * Konstruktor a felület létrehozásához.
	 */
	public HigherOrLowerGUI() {
		
		logger.info("Create a HigherOrLowerGUI.");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		
		setTitle("Higher Or Lower");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HigherOrLowerGUI.class.getResource("/11.png")));
		contentPane.setBackground(new Color(0, 153, 51));
		
		
		lblCorrectAnswers = new JLabel("Correct Answers:");
		lblCorrectAnswers.setForeground(Color.WHITE);
		lblCorrectAnswers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblWrongAnswers = new JLabel("Wrong Answers:");
		lblWrongAnswers.setForeground(Color.WHITE);
		lblWrongAnswers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		labelCorrectAnswers = new JLabel("");
		labelCorrectAnswers.setForeground(Color.WHITE);
		labelCorrectAnswers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		labelWrongAnwers = new JLabel("");
		labelWrongAnwers.setForeground(Color.WHITE);
		labelWrongAnwers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		lblPlayersName = new JLabel("Player's Name: ");
		lblPlayersName.setForeground(Color.WHITE);
		lblPlayersName.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblPlayersCash = new JLabel("Player's Cash:");
		lblPlayersCash.setForeground(Color.WHITE);
		lblPlayersCash.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		

		labelPlayerName2 = new JLabel("");
		labelPlayerName2.setForeground(Color.WHITE);
		labelPlayerName2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		labelPlayercash2 = new JLabel("");
		labelPlayercash2.setForeground(Color.WHITE);
		labelPlayercash2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		lblBet = new JLabel("Bet: ");
		lblBet.setForeground(Color.WHITE);
		lblBet.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblBetLabel = new JLabel("");
		lblBetLabel.setForeground(Color.WHITE);
		lblBetLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		lblUnusedCard = new JLabel("Unused Cards:");
		lblUnusedCard.setForeground(new Color(240, 230, 140));
		lblUnusedCard.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblAHint = new JLabel("A Hint:");
		lblAHint.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAHint.setForeground(new Color(240, 230, 140));
		
		lblHigherCards = new JLabel("Higher Cards:");
		lblHigherCards.setForeground(new Color(240, 230, 140));
		lblHigherCards.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblLowerCards = new JLabel("Lower Cards :");
		lblLowerCards.setForeground(new Color(240, 230, 140));
		lblLowerCards.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		lblUnusedCard2 = new JLabel("");
		lblUnusedCard2.setForeground(new Color(240, 230, 140));
		
		lblHigherCards2 = new JLabel("");
		lblHigherCards2.setForeground(new Color(240, 230, 140));
		
		lblLowerCards2 = new JLabel("");
		lblLowerCards2.setForeground(new Color(240, 230, 140));
		
		playerpanel = new JPanel();
		playerpanel.setBackground(new Color(0, 153, 51));
		
		deckpanel = new JPanel();
		deckpanel.setBackground(new Color(0, 153, 51));
		
		panelHint = new JPanel();
		panelHint.setBackground(new Color(0, 153, 51));
		panelHint.setVisible(false);
		
		lblResult = new JLabel("");
		lblResult.setForeground(Color.WHITE);
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblWinner = new JLabel("");
		lblWinner.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnDeal = new JButton("Deal");
		btnDeal.setEnabled(false);
		btnBet = new JButton("Bet");
		btnBet.setEnabled(false);
		btnAllIn = new JButton("All In");
		btnAllIn.setEnabled(false);
		btnHigher = new JButton("Higher");
		btnHigher.setEnabled(false);
		btnLower = new JButton("Lower");
		btnLower.setEnabled(false);
		btnNewGame = new JButton("New Game");
		btnHint = new JButton("Hint");
		btnHint.setEnabled(false);
		btnGiveUp = new JButton("Give Up");
		btnGiveUp.setEnabled(false);
		
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmHelp = new JMenuItem("Help",  new ImageIcon(HigherOrLowerGUI.class.getResource("/help_icon.png")));
		mntmHelp.setPreferredSize(new Dimension(125,25));
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		mnFile.add(mntmHelp);
		
		mntmExit = new JMenuItem("Exit",  new ImageIcon(HigherOrLowerGUI.class.getResource("/exit.png")));
		mntmExit.setPreferredSize(new Dimension(125,25));
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, ActionEvent.CTRL_MASK));
		mnFile.add(mntmExit);
		
		mntmSave = new JMenuItem("Save", new ImageIcon(HigherOrLowerGUI.class.getResource("/save.png")));
		mntmSave.setPreferredSize(new Dimension(125,25));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mnFile.add(mntmSave);
			
		
		String playername;
		
		if((playername = (String)JOptionPane.showInputDialog(null, "What is your name?", "Higher Or Lower", -1,
				new ImageIcon(HigherOrLowerGUI.class.getResource("/avatar.png")) , null, null)) != null && !playername.equals("")){
			g.getPlayer1().setName(playername);
			logger.info("Welcome the new player: {} .", playername);
		}
		else{
			JOptionPane.showMessageDialog(null, "Good Bye!");
			logger.debug("The player click the cancel button.");
			System.exit(1);
		}
		
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(HigherOrLowerGUI.this, "Are you sure you want to quit?", "Exit", JOptionPane.YES_NO_CANCEL_OPTION, -1,
						new ImageIcon(HigherOrLowerGUI.class.getResource("/quit.png")));
				if(result == 0){
					System.exit(1);
				}
			}
		});
		
		
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpTextAreaFrame = new JFrame();
				
				JTextArea helpText = new JTextArea();
				helpText.setRows(30);
				helpText.setColumns(60);
				helpText.setLineWrap(true);
				helpText.setEditable(false);
				helpText.setFont(new Font("Serif", Font.BOLD, 15));
				helpText.setForeground(Color.GRAY);
			    
				try {
					helpText.setText(IOUtils.toString(HigherOrLowerGUI.class.getResourceAsStream("/help.txt"),"iso-8859-2"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				helpText.setCaretPosition(0);
				
				JScrollPane scrollPane = new JScrollPane(helpText);
				scrollPane.setAutoscrolls(true);
				
				helpTextAreaFrame.setContentPane(scrollPane);
				helpTextAreaFrame.setLocationByPlatform(true);
				helpTextAreaFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				helpTextAreaFrame.setVisible(true);
				helpTextAreaFrame.pack();
				helpTextAreaFrame.setTitle("Help");
				helpTextAreaFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(HigherOrLowerGUI.class.getResource("/help.png")));
			}
		});
		
		
	mntmSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(g.getRoundNumber() > 0){
				
					DBUtil.save(g);
					JOptionPane.showMessageDialog(HigherOrLowerGUI.this, "JDBC processing done!", "Save", 
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon(HigherOrLowerGUI.class.getResource("/ok.png")));
					
				}else{
					JOptionPane.showMessageDialog(HigherOrLowerGUI.this, "Save Failed!\n No rounds available!", "Error",
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon(HigherOrLowerGUI.class.getResource("/error.png")));
				}
			}
		});
		
		btnDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				lblResult.setText("");
				lblWinner.setText("");
				
				if(g.getRoundNumber() == 0)
				{
					g.baseStand();
					playerpanel.removeAll();
					playerpanel.add(new JLabel(new ImageIcon("src/main/resources/default.png")));
					deckpanel.removeAll();
					deckpanel.add(new JLabel(new ImageIcon("src/main/resources/default.png")));
					deckpanel.add(new JLabel(g.getActualCard().getPicture()));
					playerpanel.updateUI();
					deckpanel.updateUI();
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
				}
				else
				{
					g.newRound();

					playerpanel.removeAll();
					playerpanel.add(new JLabel(new ImageIcon("src/main/resources/default.png")));
					deckpanel.removeAll();
					deckpanel.add(new JLabel(new ImageIcon("src/main/resources/default.png")));
					deckpanel.add(new JLabel(g.getActualCard().getPicture()));
	
					playerpanel.updateUI();
					deckpanel.updateUI();
					
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");

				}
				
				btnDeal.setEnabled(false);
				btnAllIn.setEnabled(false);
				btnHigher.setEnabled(true);
				btnLower.setEnabled(true);
				btnHint.setEnabled(true);
			}
		});
		
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				logger.info("{} is given up!", g.getPlayer1().getName());
				
				btnDeal.setEnabled(false);
				btnBet.setEnabled(false);
				btnAllIn.setEnabled(false);
				btnHigher.setEnabled(false);
				btnLower.setEnabled(false);
				btnNewGame.setEnabled(true);
				btnHint.setEnabled(false);
				btnGiveUp.setEnabled(false);
				
				lblResult.setText("");
				lblWinner.setText("YOU GIVE UP");
				
			}
		});
		
		textField_Bet = new JTextField();
		textField_Bet.setColumns(10);
		
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(isInt(textField_Bet.getText())){
					betmoney=new Integer(textField_Bet.getText());
					
					if(betmoney>0 && betmoney <= g.getPlayer1().getCash()){
				
					textField_Bet.setBackground(Color.WHITE);
					lblBetLabel.setText((int)betmoney+"  ");
					btnBet.setEnabled(false);
					btnAllIn.setEnabled(false);
					
					}
					
					else{
					textField_Bet.setBackground(Color.RED);
					JOptionPane.showMessageDialog(HigherOrLowerGUI, "Maximum bet: "+ (int)g.getPlayer1().getCash() + " !", "Info",
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon(HigherOrLowerGUI.class.getResource("/info.png")));
					textField_Bet.setText("");
					textField_Bet.setBackground(Color.WHITE);
					
					btnBet.setEnabled(true);
					btnAllIn.setEnabled(true);
					
				}
			}
			else{
				textField_Bet.setBackground(Color.RED);
				JOptionPane.showMessageDialog(HigherOrLowerGUI.this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE,
						new ImageIcon(HigherOrLowerGUI.class.getResource("/error_1.png")));
				textField_Bet.setText("");
				textField_Bet.setBackground(Color.WHITE);
				
				btnBet.setEnabled(true);
				btnAllIn.setEnabled(true);
				
			}
				
				
				btnDeal.setEnabled(true);
				btnGiveUp.setEnabled(false);
				
				
			}
		});
		

		btnAllIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				betmoney = g.getPlayer1().getCash();
				
				lblBetLabel.setText((int)betmoney+"");
				
				labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
				
				btnBet.setEnabled(false);
				btnDeal.setEnabled(true);
				btnGiveUp.setEnabled(false);
				
				logger.trace("ALL IN!");
				
			}
		});
		
		
		btnHigher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				g.checkHigher();
				
				if(g.isEqualCards() == true)
				{
					lblResult.setText("Equal Cards");
					
					playerpanel.removeAll();
					playerpanel.add(new JLabel(g.getPlayer1().getCurCard().getPicture()));
					panelHint.setVisible(false);
					panelHint.updateUI();
					playerpanel.updateUI();
				}
				else
				{
					if(g.isWin() == true){
						g.playerWin(betmoney);
						
						lblResult.setText("Correct Answer");
					}
					else{
						g.playerLose(betmoney);
						
						lblResult.setText("Wrong Answer");
					}
					
					playerpanel.removeAll();
					playerpanel.add(new JLabel(g.getPlayer1().getCurCard().getPicture()));
					playerpanel.updateUI();
					
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
				}
				
				g.checkEnd();
				
				if(g.isGameOver() == true)
				{
					lblWinner.setText("GAME OVER");
					
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
					btnGiveUp.setEnabled(false);
					btnLower.setEnabled(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnBet.setEnabled(false);
					btnAllIn.setEnabled(false);
					btnNewGame.setEnabled(true);
					
				}
				
				if(g.isFinalWin() == true)
				{
					lblWinner.setText("WINNER GAME");
					
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
					btnLower.setEnabled(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnBet.setEnabled(false);
					btnGiveUp.setEnabled(false);
					btnAllIn.setEnabled(true);
					btnNewGame.setEnabled(true);
				}
				if(g.isFinalWin() == false && g.isGameOver() == false)
				{
					textField_Bet.setText("");
					lblBetLabel.setText("");
					panelHint.setVisible(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnLower.setEnabled(false);
					btnBet.setEnabled(true);
					btnGiveUp.setEnabled(true);
					btnNewGame.setEnabled(false);
					btnAllIn.setEnabled(true);
				}
			}
		});
		

		btnLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				g.checkLower();
				
				if(g.isEqualCards() == true)
				{
					lblResult.setText("Equal Cards");
					
					playerpanel.removeAll();
					playerpanel.add(new JLabel(g.getPlayer1().getCurCard().getPicture()));
					playerpanel.updateUI();
				}
				else
				{
					if(g.isWin() == true){
						g.playerWin(betmoney);
						
						lblResult.setText("Correct Answer");
					}
					else{
						g.playerLose(betmoney);
						
						lblResult.setText("Wrong Answer");
					}
					
					playerpanel.removeAll();
					playerpanel.add(new JLabel(g.getPlayer1().getCurCard().getPicture()));
					playerpanel.updateUI();
					
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
				}
				
				g.checkEnd();
				
				if(g.isGameOver() == true){
					
					lblWinner.setText("GAME OVER");
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
					btnLower.setEnabled(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnBet.setEnabled(false);
					btnGiveUp.setEnabled(false);
					btnAllIn.setEnabled(false);
					btnNewGame.setEnabled(true);
				}
				
				if(g.isFinalWin() == true){
					lblWinner.setText("WINNER GAME");
					
					labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
					labelWrongAnwers.setText(g.getWrongAnswers()+"");
					labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
					
					btnLower.setEnabled(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnBet.setEnabled(false);
					btnGiveUp.setEnabled(false);
					btnAllIn.setEnabled(false);
					btnNewGame.setEnabled(true);
				}
				
				if(g.isFinalWin() == false && g.isGameOver() == false)
				{
					textField_Bet.setText("");
					lblBetLabel.setText("");
					panelHint.setVisible(false);
					btnLower.setEnabled(false);
					btnHint.setEnabled(false);
					btnHigher.setEnabled(false);
					btnBet.setEnabled(true);
					btnGiveUp.setEnabled(true);
					btnAllIn.setEnabled(true);
					btnNewGame.setEnabled(false);
				}
			}
		});
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				g.newGame();
				g.getPlayer1().setCash(10000);
				deckpanel.removeAll();
				playerpanel.removeAll();
				
				lblWinner.setText("");
				lblResult.setText("");
				lblBetLabel.setText("");
				textField_Bet.setText("");
				
				labelPlayerName2.setText(g.getPlayer1().getName());
				labelPlayercash2.setText((int)g.getPlayer1().getCash()+"");
				labelCorrectAnswers.setText(g.getCorrectAnswers()+"");
				labelWrongAnwers.setText(g.getWrongAnswers()+"");
				
				deckpanel.add(new JLabel(new ImageIcon("src/main/resources/default.png")));
				deckpanel.updateUI();
				panelHint.setVisible(false);
				playerpanel.updateUI();
				
				btnNewGame.setEnabled(false);
				btnBet.setEnabled(true);
				btnAllIn.setEnabled(true);
			}
			
			
		});
		
		btnHint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				logger.info("{} is get a hint!", g.getPlayer1().getName());
				panelHint.setVisible(true);
				g.getHint();
				lblHigherCards2.setText(g.getHigherCards()+"   "+ ((g.getHigherCards()*100)/g.getUnusedCards())+"%");
				lblLowerCards2.setText(g.getLowerCards()+"  " + ((g.getLowerCards()*100)/g.getUnusedCards())+"%");
				lblUnusedCard2.setText(g.getUnusedCards()+"");
								
				btnHint.setEnabled(false);
			}
		});
		
				
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewGame)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(157)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(playerpanel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnHigher)
											.addGap(18)
											.addComponent(btnLower))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(btnGiveUp)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnDeal)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnHint)
									.addGap(46))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(deckpanel, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblWinner, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblWrongAnswers)
										.addComponent(lblCorrectAnswers)
										.addComponent(lblPlayersCash)
										.addComponent(lblPlayersName)
										.addComponent(lblBet))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblBetLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(labelCorrectAnswers, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(labelWrongAnwers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addComponent(labelPlayercash2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
													.addComponent(labelPlayerName2, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAllIn)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBet)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_Bet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(67))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(panelHint, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(lblWinner, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(29))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelHint, 0, 0, Short.MAX_VALUE)
								.addComponent(deckpanel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(playerpanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(labelPlayerName2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPlayersName))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPlayersCash)
								.addComponent(labelPlayercash2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCorrectAnswers)
								.addComponent(labelCorrectAnswers, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblWrongAnswers)
								.addComponent(labelWrongAnwers, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(8)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHigher)
							.addComponent(btnLower)
							.addComponent(lblBet))
						.addComponent(lblBetLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAllIn)
							.addComponent(btnBet)
							.addComponent(textField_Bet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnHint))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewGame)
								.addComponent(btnGiveUp)
								.addComponent(btnDeal))))
					.addContainerGap())
		);
		
		
		GroupLayout gl_panelHint = new GroupLayout(panelHint);
		gl_panelHint.setHorizontalGroup(
			gl_panelHint.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHint.createSequentialGroup()
					.addGroup(gl_panelHint.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHint.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelHint.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUnusedCard)
								.addComponent(lblHigherCards)
								.addComponent(lblLowerCards))
							.addGap(18)
							.addGroup(gl_panelHint.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUnusedCard2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelHint.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblLowerCards2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblHigherCards2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))))
						.addGroup(gl_panelHint.createSequentialGroup()
							.addGap(61)
							.addComponent(lblAHint)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelHint.setVerticalGroup(
			gl_panelHint.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHint.createSequentialGroup()
					.addComponent(lblAHint)
					.addGap(19)
					.addGroup(gl_panelHint.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUnusedCard)
						.addComponent(lblUnusedCard2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelHint.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHigherCards)
						.addComponent(lblHigherCards2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelHint.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLowerCards)
						.addComponent(lblLowerCards2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		panelHint.setLayout(gl_panelHint);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	/**Ellenőrzi, hogy a paraméterül adott sztring kasztolható-e egésszé.
	 * @param s az ellenőrizendő sztring
	 * @return <code>true</code>, ha a sztring kasztolható, <code>false</code> egyébként
	 */
	public boolean isInt(String s)
	{
		try{
			int i = Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException er){
			logger.warn("{} is invalid input", s);
			return false; 
		}
	}
}
