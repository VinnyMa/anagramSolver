// Name: Vincent Ma
// TA: Benjamin MacMiller
// CSE 143
// Assignment #6
// This class uses a dictionary of words to find any combination of words that have the same
// letters as the given phrase by the client.

import java.util.*;

public class AnagramSolver{
   private List<String> dictionaryList;
   private List<String> possibleList;
   
   // pre: accepts a list of strings that acts as a dictionary
   // post: constructs an anagram solver using the given dictionary
   public AnagramSolver(List<String> list){
      dictionaryList = list;
      possibleList = new ArrayList<String>();
   }
   
   // pre: accepts a string as the client's given phrase
   //      accepts an int as the maximum number of words to use (0 if there is no max)
   //      if the max given is less than 0, throws an IllegalArgumentException
   // post: finds and prints all combinations of words that have the same combination of
   //       letters as the given phrase
   public void print(String s, int max){
      if(max < 0){
         throw new IllegalArgumentException();
      }
      LetterInventory word = new LetterInventory(s);
      for(String dictWord: dictionaryList){
         LetterInventory possWord = new LetterInventory(dictWord);
         if(word.subtract(possWord) != null){
            possibleList.add(dictWord);
         }
      }
      Stack<String> solution = new Stack<String>();
      anagrams(word, solution, max);
      possibleList.clear();
   }
   
   // pre: accepts a LetterInventory of the client's phrase
   //      accepts a Stack of words as a potential solution
   //      accepts an int maximum number of words to use (0 if there is no max)
   // post: systematically goes through the given dictionary, then finds and prints
   //       all combinations of words that have the same letters as the given phrase
   private void anagrams(LetterInventory word, Stack<String> solution, int max){
      if(word.isEmpty()){
         System.out.println(solution.toString());
      }else if(max == 0 || solution.size() < max){
         for(String s: possibleList){
            LetterInventory possSolution = new LetterInventory(s);
            if(word.subtract(possSolution) != null){
               solution.push(s);
               word = word.subtract(possSolution);
               anagrams(word, solution, max);
               solution.pop();
               word = word.add(possSolution);
            }
         }
      }
   }
}