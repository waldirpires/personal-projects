package br.edu.pitagoras.pa.trie;

public class PrefixTree {
    static TrieNode createTree() {
        return (new TrieNode('\0'));
    }

    static void insertWord(TrieNode root, String word) {
        // Algoritmo Inserir_Arvore_Digital(raiz, palavra)
        // Converter a palavra em cadeia de caracteres
        // Para cada letra na palavra
        //   Se o nó corrente não possuir a letra
        //     Adicionar um novo nó no nó corrente com a letra corrente
        //   Atualizar o nó corrente para ser o novo nó criado
        // Atualizar o último nó para terminal (palavra completa)

        int offset = 97;
        int l = word.length();
        char[] letters = word.toCharArray();
        TrieNode curNode = root;

        for (int i = 0; i < l; i++) {
            if (curNode.links[letters[i] - offset] == null) {
                curNode.links[letters[i] - offset] = new TrieNode(letters[i]);
            }
            curNode = curNode.links[letters[i] - offset];
        }
        curNode.fullWord = true;
    }

    static boolean find(TrieNode root, String word) {
        // Algoritmo Busca_Arvore_Digital(raiz, palavra)
        // converter a palavra em cadeia de caracteres
        // Para cada letra da cadeia correspondente da palavra
        //   Se o nó atual for nulo
        //     Retornar vazio
        //   obter o nó referente a letra a partir do nó corrente
        // Se o laco foi executado até o final e o ultimo nó for nulo
        //   Retornar falso
        // Senão houver nó mas este nó náo é terminal
        //   Retornar falso
        // Senão
        //   Retornar verdadeiro

        char[] letters = word.toCharArray();
        int l = letters.length;
        int offset = 97;
        TrieNode curNode = root;

        int i;
        for (i = 0; i < l; i++) {
            if (curNode == null) {
                return false;
            }
            curNode = curNode.links[letters[i] - offset];
        }

        if ((i == l) && (curNode == null)) {
            return false;
        }

        if ((curNode != null) && !curNode.fullWord) {
            return false;
        }

        return true;
    }

    static void printTree(TrieNode root, int level, char[] branch) {
        // Algoritmo Imprime_Arvore_Digital(Raiz, nível, galho)
        // Se a raiz for nula
        //   encerrar o algoritmo
        // Para cada letra existente no nó corrente
        //   Obter os galhos do nível
        //   Imprime_Arvore_Digital(nó correspondente a letra corrente, nível + 1, galho)
        // Se o nó for terminal
        //   Para cada galho encontrado
        //     Escrever o galho na tela

        if (root == null) {
            return;
        }

        for (TrieNode link : root.links) {
            branch[level] = root.letter;
            printTree(link, level + 1, branch);
        }

        if (root.fullWord) {
            for (int j = 1; j <= level; j++) {
                System.out.print(branch[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TrieNode tree = createTree();

        String[] words = { "an", "ant", "all", "allot", "alloy", "aloe", "are", "ate", "be" };
        for (String word : words) {
            insertWord(tree, word);
        }

        char[] branch = new char[50];
        printTree(tree, 0, branch);

        String searchWord = "all";
        if (find(tree, searchWord)) {
            System.out.println("The word was found");
        } else {
            System.out.println("The word was NOT found");
        }
    }
}
