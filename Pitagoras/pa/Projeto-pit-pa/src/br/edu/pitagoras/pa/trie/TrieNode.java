package br.edu.pitagoras.pa.trie;

class TrieNode // nó da árvore digital
{   char letter; // letra contida no nó
    TrieNode[] links; // lista de outros nós
    boolean fullWord; // informar se completa uma palavra
  
    TrieNode(char letter, boolean fullWord)
    {   this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = fullWord;
    }
    
    TrieNode(char letter)
    {   this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = false;
    }    
}