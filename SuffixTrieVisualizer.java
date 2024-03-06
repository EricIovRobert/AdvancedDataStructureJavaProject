package suffix;

import javax.swing.*;
import java.awt.*;

/**
 * O aplicație pentru vizualizarea și construirea unui Trie pentru căutarea de motive ADN
 */
public class SuffixTrieVisualizer extends JFrame {
	private static final long serialVersionUID = 1L;
    private JTextField inputField;
    private SuffixTriePanel treePanel;
    
    /**
     * Inițializează fereastra de vizualizare a Trie-ului pentru căutarea de motive ADN
     */
    public SuffixTrieVisualizer() {
        super("Motif Trie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(792, 707);
        getContentPane().setLayout(new BorderLayout());

        treePanel = new SuffixTriePanel();
        getContentPane().add(treePanel);
        treePanel.setLayout(null);
        
                inputField = new JTextField();
                inputField.setBounds(10, 23, 758, 19);
                treePanel.add(inputField);
                
                JLabel lblNewLabel = new JLabel("Insert motifs and then press ENTER to visualize the Trie");
                lblNewLabel.setBounds(10, 0, 530, 13);
                treePanel.add(lblNewLabel);
                inputField.addActionListener(e -> buildAndShowSuffixTree());

        setVisible(true);
    }
    
    /**
     * Verifică dacă motivele sunt valide (conțin doar caracterele A, C, G, T)
     *
     * @param motifs Motivele de verificat
     * @return true dacă motivele sunt valide, false altfel
     */

    private boolean areValidMotifs(String[] motifs) {
        for (String motif : motifs) {
            if (!motif.matches("[ACGT]+")) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Construiește și afișează Trie-ul
     */
    private void buildAndShowSuffixTree() {
        //String text = inputField.getText().trim();
        String[] motifs = inputField.getText().trim().split(",");
        
        if (inputField.getText().trim().isEmpty() || motifs.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter the motifs",
                                          "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!areValidMotifs(motifs))
        {
        	JOptionPane.showMessageDialog(this, "Please enter valid motifs",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
        SuffixTrieNode root = new SuffixTrieNode();
        for (int i = 0; i < motifs.length; i++) {
            root.insertSuffix(motifs[i],i+1);
        }
        //root.insertSuffix("");
        treePanel.setRoot(root);
    }
    }
    /**
     * Main-ul
     *
     * @param args 
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuffixTrieVisualizer frame = new SuffixTrieVisualizer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
