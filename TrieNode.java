package suffix;

import java.util.*;


/**
 * Nodul folosit în Trie-ul pentru vizualizare.
 */
class SuffixTrieNode {
	
    private Map<String, SuffixTrieNode> children = new HashMap<>();

    /**
     * Inserează un motiv în Trie
     *
     * @param suffix motivul de inserat
     */
    public void insertSuffix(String suffix,int i) {
        if (suffix.isEmpty()) {	
        	String x = String.valueOf(i);
            children.put(x, new SuffixTrieNode()); 
            return;
        }

        String firstChar = String.valueOf(suffix.charAt(0));
        children.putIfAbsent(firstChar, new SuffixTrieNode());
        children.get(firstChar).insertSuffix(suffix.substring(1),i);
    }

    /**
     * Obține copiii nodului curent.
     *
     * @return HashMap care conține copiii nodului
     */
    public Map<String, SuffixTrieNode> getChildren() {
        return children;
    }
}
