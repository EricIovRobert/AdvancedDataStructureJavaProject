package proiectSDA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * Interfață grafică pentru căutarea de motive într-o secvență de ADN utilizând algoritmul Aho-Corasick.
 */
public class DNA_Search extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField dnaSequenceField;
    private JTextField motifsField;
    private JButton searchButton;
    private JButton exitButton;
    private JTextPane resultsPane;

    /**
     * Main-ul. Inițializează și afișează fereastra principală
     *
     * @param args 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DNA_Search frame = new DNA_Search();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Creează și inițializează fereastra principală a interfetei
     */
    public DNA_Search() {
        setTitle("DNA Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        dnaSequenceField = new JTextField(20);
        motifsField = new JTextField(20);

        Dimension buttonDimension = new Dimension(230, 30);

        
        GridBagConstraints gbcLabel1 = new GridBagConstraints();
        gbcLabel1.gridwidth = GridBagConstraints.REMAINDER;
        gbcLabel1.fill = GridBagConstraints.HORIZONTAL;
        gbcLabel1.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("DNA Sequence:"), gbcLabel1);

    
        GridBagConstraints gbcDNAField = new GridBagConstraints();
        gbcDNAField.gridwidth = GridBagConstraints.REMAINDER;
        gbcDNAField.fill = GridBagConstraints.HORIZONTAL;
        gbcDNAField.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(dnaSequenceField, gbcDNAField);

     
        GridBagConstraints gbcLabel2 = new GridBagConstraints();
        gbcLabel2.gridwidth = GridBagConstraints.REMAINDER;
        gbcLabel2.fill = GridBagConstraints.HORIZONTAL;
        gbcLabel2.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(new JLabel("Motifs (comma separated):"), gbcLabel2);

     
        GridBagConstraints gbcMotifsField = new GridBagConstraints();
        gbcMotifsField.gridwidth = GridBagConstraints.REMAINDER;
        gbcMotifsField.fill = GridBagConstraints.HORIZONTAL;
        gbcMotifsField.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(motifsField, gbcMotifsField);

    
        GridBagConstraints gbcSearchButton = new GridBagConstraints();
        gbcSearchButton.gridwidth = 1;
        gbcSearchButton.fill = GridBagConstraints.HORIZONTAL;
        gbcSearchButton.insets = new Insets(5, 5, 5, 5);
        searchButton = new JButton("Search");
        searchButton.setPreferredSize(buttonDimension);
        getContentPane().add(searchButton, gbcSearchButton);

      
        GridBagConstraints gbcExitButton = new GridBagConstraints();
        gbcExitButton.gridx = 1;
        gbcExitButton.fill = GridBagConstraints.HORIZONTAL;
        gbcExitButton.insets = new Insets(5, 5, 5, 5);
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(buttonDimension);
        getContentPane().add(exitButton, gbcExitButton);

       
        GridBagConstraints gbcScrollPane = new GridBagConstraints();
        gbcScrollPane.gridx = 0;
        gbcScrollPane.gridy = GridBagConstraints.RELATIVE;
        gbcScrollPane.gridwidth = GridBagConstraints.REMAINDER;
        gbcScrollPane.fill = GridBagConstraints.BOTH;
        gbcScrollPane.weightx = 1.0;
        gbcScrollPane.weighty = 1.0;
        resultsPane = new JTextPane();
        resultsPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsPane);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        getContentPane().add(scrollPane, gbcScrollPane);

        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        exitButton.addActionListener(e -> dispose());
    }

    /**
     * Execută căutarea de motive în secvența de ADN și afișează rezultatele
     */
    private void performSearch() {
        String text = dnaSequenceField.getText().trim();
        String[] motifs = motifsField.getText().trim().split(",");
        if (text.isEmpty() || motifsField.getText().trim().isEmpty()) {
            resultsPane.setText("Please enter the DNA sequence and/or motifs.");
            return;
        }
        if (isValidDNA(text) && areValidMotifs(motifs)) {
            String results = AhoCorasick.searchWords(motifs, motifs.length, text);
            if (results.isEmpty()) {
                resultsPane.setText("No motifs were found.");
            } else {
                displayResultsWithHighlighting(text, motifs);
            }
        } else {
            resultsPane.setText("The text or motifs contain invalid characters.");
        }
    }

    /**
     * Afișează rezultatele căutării cu evidențiere în secvența de ADN
     *
     * @param text   Secvența de ADN
     * @param motifs Motivele căutate
     */
    private void displayResultsWithHighlighting(String text, String[] motifs) {
        StyledDocument doc = resultsPane.getStyledDocument();
        SimpleAttributeSet yellowHighlight = new SimpleAttributeSet();
        StyleConstants.setBackground(yellowHighlight, Color.YELLOW);
        SimpleAttributeSet greenHighlight = new SimpleAttributeSet();
        StyleConstants.setBackground(greenHighlight, Color.GREEN);

        try {
            text += "\n";
            doc.remove(0, doc.getLength());
            doc.insertString(0, text, null);

            boolean useYellow = true;
            StringBuilder positionsInfo = new StringBuilder();

            for (String motif : motifs) {
                Pattern pattern = Pattern.compile(Pattern.quote(motif));
                Matcher matcher = pattern.matcher(text);
                ArrayList<Integer> positions = new ArrayList<>();
                boolean found = false;

                while (matcher.find()) {
                    SimpleAttributeSet highlight = useYellow ? yellowHighlight : greenHighlight;
                    doc.setCharacterAttributes(matcher.start(), motif.length(), highlight, false);
                    positions.add(matcher.start());
                    useYellow = !useYellow;
                }

                if (!positions.isEmpty()) {
                    positionsInfo.append("Motif ").append(motif).append(" found at positions ");
                    for (Integer position : positions) {
                        positionsInfo.append(position + 1).append(", ");
                    }
                    positionsInfo.setLength(positionsInfo.length() - 2);
                    positionsInfo.append("\n");
                    found = true;
                }
                if (!found) {
                    positionsInfo.append("Motif ").append(motif).append(" not found\n");
                }

            }

            doc.insertString(doc.getLength(), positionsInfo.toString(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifică dacă o secvență de ADN este validă
     *
     * @param text Secvența de ADN
     * @return true dacă secvența este validă, false altfel
     */

    private boolean isValidDNA(String text) {
        return text.matches("[ACGT]+");
    }

    /**
     * Verifică dacă motivele sunt valide (conțin doar caracterele A, C, G, T).
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
}
