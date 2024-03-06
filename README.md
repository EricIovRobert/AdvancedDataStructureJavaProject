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
https://www.geeksforgeeks.org/aho-corasick-algorithm-pattern-searching/
https://www.geeksforgeeks.org/trie-insert-and-search/
https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/
https://docs.oracle.com/javase/8/docs/api/java/awt/GridBagConstraints.html
https://stackoverflow.com/questions/33342521/java-gridbaglayout-and-gridbagconstraints
https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
https://stackoverflow.com/questions/6530105/highlighting-text-in-java
https://docs.oracle.com/javase/8/docs/api/index.html?javax/swing/text/Highlighter.html

