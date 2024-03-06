# DNA Motif Search / Motif Visualizer
### Iov Eric-Robert

## Descriere
Proiectul de DNA Motif Search este o unealtă de bioinformatică care permite utilizatorilor să caute subsecvențe de nucleotide într-o secvență de ADN dată. Aplicația utilizează algoritmul Aho-Corasick pentru o potrivire eficientă a motivelor. Utilizatorii pot introduce o secvență de ADN și o listă de motive (separate prin virgulă), iar instrumentul va evidenția aparițiile acestor motive în secvență.
Pentru partea de vizualizare, se vor introduce motivele căutate și pe baza acestora se va realiza arborele de chei care poate fi folosit la căutarea lor în secvența de ADN.

## Obiective
Principalele obiective ale proiectului includ:

* Permițerea utilizatorilor să introducă o secvență de ADN și o listă de motive.
* Implementarea algoritmului Aho-Corasick pentru căutarea motivelor.
* Evidențierea aparițiilor motivelor în secvența de ADN.
* Găsirea cât mai eficientă și rapidă a secvențelor de nucleotide căutate
* Vizualizarea motivelor căutate într-un arbore de chei

## Structuri de date folosite

* Matricea de tranziție (g): O matrice care stochează tranzițiile între stările automatului Aho-Corasick pentru fiecare caracter posibil din alfabetul ADN (A, C, G, T).
* Coada: Utilizată în procesul de construire a automatului Aho-Corasick pentru gestionarea stărilor.
* Automatul finit (automatul de căutare este implementat folosind un arbore de trie, care este reprezentat în cod prin array-uri bidimensionale (g) in care se stochează tranzițiile între stările automate, array-ul out este folosit pentru a păstra informații despre cuvintele cheie care se potrivesc la o anumită stare, array-ul f reprezintă funcția de eșec, care indică către care stare să se mute automatul în cazul unei lipse de potrivire).
* Trie.


## Functionalitati/Exemple utilizare/Teste/Benchmark
* Căutarea Motivelor: Utilizatorii pot introduce o secvență de ADN și o listă de motive pentru a căuta aparițiile acestor motive în secvență.
* Evidențierea Aparițiilor: Aplicația evidențiază aparițiile motivelor găsite în secvența de ADN, facilitând identificarea acestora.
* Posibilitatea de vizualizare a arborelui de chei format din motivele introduse de utilizator .
* Utilizatorul introduce o secvență ADN și subsecvențele pe care dorește să le găsească pentru aflarea de informații, găsirea de pattern-uri și alte utilități ale determinării secvențelor.

### Resurse
[GeeksforGeeks - Aho-Corasick Algorithm](https://www.geeksforgeeks.org/aho-corasick-algorithm-pattern-searching/)
[GeeksforGeeks - Trie Insertion and Search](https://www.geeksforgeeks.org/trie-insert-and-search/)
[Java Swing JPanel Examples](https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/)
[Java AWT GridBagConstraints](https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html)
[Java GridBagLayout and GridBagConstraints](https://stackoverflow.com/questions/33342521/java-gridbaglayout-and-gridbagconstraints)
[Java AWT Graphics](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html)
[Highlighting Text in Java](https://stackoverflow.com/questions/6530105/highlighting-text-in-java)
[Java Swing text Highlighter](https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/text/Highlighter.html)

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# DNA Motif Search / Motif Visualizer
### Iov Eric-Robert

## Description
The DNA Motif Search project is a bioinformatics tool that allows users to search for nucleotide subsequences in a given DNA sequence. The application utilizes the Aho-Corasick algorithm for efficient motif matching. Users can input a DNA sequence and a list of motifs (comma-separated), and the tool will highlight the occurrences of these motifs in the sequence.
For the visualization part, the searched motifs will be entered and based on them the key tree will be made that can be used for searching in the DNA sequence.

## Objectives
The main objectives of the project include:

* Allowing users to input a DNA sequence and a list of motifs.
* Implementing the Aho-Corasick algorithm for motif searching.
* Highlighting occurrences of motifs in the DNA sequence.
* Finding nucleotide sequences as efficiently and quickly as possible.
* Visualizing the searched motifs in a key tree.

## Data Structures Used

* Transition Matrix (g): A matrix that stores transitions between states of the Aho-Corasick automaton for each possible character from the DNA alphabet (A, C, G, T).
* Queue: Used in the process of building the Aho-Corasick automaton for managing states.
* Finite Automaton (the search automaton is implemented using a trie tree, which is represented in the code by two-dimensional arrays (g) that store transitions between automaton states, the out array is used to keep information about the keywords that match a certain state, the f array represents the failure function, which indicates which state the automaton should move to in case of a mismatch).
* Trie.

## Features/Usage Examples/Tests/Benchmark
* Motif Search: Users can input a DNA sequence and a list of motifs to search for occurrences of these motifs in the sequence.
* Highlighting Occurrences: The application highlights the found occurrences of motifs in the DNA sequence, facilitating their identification.
* Possibility of visualizing the key tree formed from the user-input motifs.
* The user inputs a DNA sequence and the subsequences they want to find for information retrieval, pattern finding, and other sequence determination utilities.

### Resources
[GeeksforGeeks - Aho-Corasick Algorithm](https://www.geeksforgeeks.org/aho-corasick-algorithm-pattern-searching/)
[GeeksforGeeks - Trie Insertion and Search](https://www.geeksforgeeks.org/trie-insert-and-search/)
[Java Swing JPanel Examples](https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/)
[Java AWT GridBagConstraints](https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html)
[Java GridBagLayout and GridBagConstraints](https://stackoverflow.com/questions/33342521/java-gridbaglayout-and-gridbagconstraints)
[Java AWT Graphics](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html)
[Highlighting Text in Java](https://stackoverflow.com/questions/6530105/highlighting-text-in-java)
[Java Swing text Highlighter](https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/text/Highlighter.html)

