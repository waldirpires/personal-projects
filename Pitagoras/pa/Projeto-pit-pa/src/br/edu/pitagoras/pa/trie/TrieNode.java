package br.edu.pitagoras.pa.trie;

class TrieNode // n� da �rvore digital
{   char letter; // letra contida no n�
    TrieNode[] links; // lista de outros n�s
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