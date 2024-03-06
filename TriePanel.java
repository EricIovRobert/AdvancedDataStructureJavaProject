package suffix;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Panoul responsabil pentru afișarea Trie-ului pentru căutarea de motive ADN
 */
class SuffixTriePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/**
	* Inițializează panoul
	*/
	public SuffixTriePanel() {
		setLayout(null);
	}
    private SuffixTrieNode root;

    
    /**
     * Setează rădăcina Trie-ului
     *
     * @param root 
     */
    public void setRoot(SuffixTrieNode root) {
        this.root = root;
        repaint();
    }

   
    /**
     * Suprascrie metoda paintComponent pentru a desena Trie-ul pe panou
     *
     * @param g Graphic  context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            drawNode(g, root, getWidth() / 2, 70, getWidth() / 4);
        }
    }

    /**
     * Desenează un nod și liniile către copiii săi
     *
     * @param g      Graphic  context
     * @param node   Nodul de desenat
     * @param x      Coordonata x a centrului nodului
     * @param y      Coordonata y a centrului nodului
     * @param space  Spatiul dintre noduri
     */
    private void drawNode(Graphics g, SuffixTrieNode node, int x, int y, int space) {
        Set<Map.Entry<String, SuffixTrieNode>> entries = node.getChildren().entrySet();
        int deltaX = space / 2;
        int deltaY = 50;
        int childX = x - (entries.size() - 1) * deltaX / 2;
        int childY = y + deltaY;

        for (Map.Entry<String, SuffixTrieNode> entry : entries) {
            g.drawLine(x, y, childX, childY);
            g.drawOval(childX - 5, childY - 5, 10, 10);
            g.drawString(entry.getKey(), childX, childY - 10);
            drawNode(g, entry.getValue(), childX, childY, deltaX);
            childX += deltaX;
        }
    }
}
