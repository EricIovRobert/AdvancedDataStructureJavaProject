package proiectSDA;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Implementare a algoritmului Aho-Corasick pentru căutarea multiplă a șirurilor
 */

public class AhoCorasick {
	/**
     * Numărul maxim de stări în trie
     */
    static final int MAXS = 500;
    /**
     * Numărul de caractere posibile în alfabetul de intrare, S = {A, T, C, G}
     */
    static final int MAXC = 4;
    /**
     * Matricea pentru a stoca dacă o stare este o stare de ieșire pentru un șir specific
     */
    static boolean[][] out = new boolean[MAXS][MAXS];
    /**
     * Funcția de eșec pentru fiecare stare
     */
    static int[] f = new int[MAXS];
    /**
     * Funcția de tranziție pentru fiecare stare și caracter din alfabet
     */
    static int[][] g = new int[MAXS][MAXC];

    
    /**
     * Construiește "automatul" format din trie și failure link-urile acestuia
     *
     * @param arr motivele cautate
     * @param k   Numărul de șiruri în array
     * @return Numărul de stări pe care le are "automatul"
     */
    public static int buildMatchingMachine(String[] arr, int k) {
        for (int i = 0; i < MAXS; i++) {
            Arrays.fill(g[i], -1);
            Arrays.fill(out[i], false);
        }
        int states = 1;

        for (int i = 0; i < k; ++i) {
            String word = arr[i];
            int currentState = 0;
            for (int j = 0; j < word.length(); ++j) {
                int ch = charToIndex(word.charAt(j));
                if (ch == -1) {
                    System.err.println("Caracter nevalid în cuvânt: " + word);
                    return 0;
                }
                if (g[currentState][ch] == -1) g[currentState][ch] = states++;
                currentState = g[currentState][ch];
            }
            out[currentState][i] = true;
        }

        for (int ch = 0; ch < MAXC; ++ch) if (g[0][ch] == -1) g[0][ch] = 0;
        Arrays.fill(f, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int ch = 0; ch < MAXC; ++ch) {
            if (g[0][ch] != 0) {
                f[g[0][ch]] = 0;
                q.add(g[0][ch]);
            }
        }

        while (!q.isEmpty()) {
            int state = q.poll();
            for (int ch = 0; ch < MAXC; ++ch) {
                if (g[state][ch] != -1) {
                    int failure = f[state];
                    while (g[failure][ch] == -1) failure = f[failure];
                    failure = g[failure][ch];
                    f[g[state][ch]] = failure;
                    out[g[state][ch]] = merge(out[g[state][ch]], out[failure]);
                    q.add(g[state][ch]);
                }
            }
        }
        return states;
    }
    /**
     * Găsește următoarea stare.
     *
     * @param currentState Starea curentă
     * @param nextInput   Caracterul de intrare următor
     * @return Următoarea stare
     */
    public static int findNextState(int currentState, char nextInput) {
        int answer = currentState;
        int ch = charToIndex(nextInput);
        if (ch == -1) {
            return currentState;
        }
        while (g[answer][ch] == -1) answer = f[answer];
        return g[answer][ch];
    }

    /**
     * Caută motivele specificate în secvența de ADN și returnează pozițiile în care apar
     *
     * @param arr  Motivele pentru căutare
     * @param k    Numărul de motive
     * @param text ADN 
     * @return Pozițiile în care apar secventele în ADN
     */
    public static String searchWords(String[] arr, int k, String text) {
        StringBuilder results = new StringBuilder();
        buildMatchingMachine(arr, k);
        int currentState = 0;
        for (int i = 0; i < text.length(); ++i) {
            currentState = findNextState(currentState, text.charAt(i));
            if (!anyTrue(out[currentState])) continue;
            for (int j = 0; j < k; ++j) {
                if (out[currentState][j]) {
                    results.append("Word ").append(arr[j])
                            .append(" appears from ")
                            .append(i - arr[j].length() + 2)
                            .append(" to ").append(i + 1).append("\n");
                }
            }
        }
        return results.toString();
    }

    /**
     * Realizează o combinație logică de tip OR între doi vectori de tip boolean.
     * Această metodă este utilizată în implementarea algoritmului Aho-Corasick pentru construirea
     * și actualizarea stărilor de ieșire ale automatului
     *
     * @param arr1 Primul vector boolean
     * @param arr2 Al doilea vector boolean
     * @return Un nou vector boolean rezultat din aplicarea operației OR între elementele corespunzătoare ale celor doi vectori
     */
    static boolean[] merge(boolean[] arr1, boolean[] arr2) {
        boolean[] result = new boolean[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] || arr2[i];
        }
        return result;
    }

    /**
     * Verifică dacă există cel puțin un element true în tabloul boolean
     *
     * @param arr Tabloul boolean de verificat
     * @return True dacă există cel puțin un element true, false în caz contrar
     */

    static boolean anyTrue(boolean[] arr) {
        for (boolean b : arr) {
            if (b) return true;
        }
        return false;
    }

    /**
     * Converteste un caracter la indexul corespunzător în alfabet
     *
     * @param c Caracterul de intrare
     * @return Indexul caracterului în alfabet, sau -1 dacă caracterul este invalid
     */
    static int charToIndex(char c) {
        return switch (c) {
            case 'A' -> 0;
            case 'C' -> 1;
            case 'G' -> 2;
            case 'T' -> 3;
            default -> -1;
        };
    }
}
