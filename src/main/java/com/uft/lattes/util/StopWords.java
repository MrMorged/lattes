/*
 * Copyright 2014 adelmir.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uft.lattes.util;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author adelmir
 */
public class StopWords {
    
     private final static String[] ALFABETO = {
        "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "x", "y", "w", "z"};

    private final static String[] BRAZILIAN_STOP_WORDS = {
        "a", "ainda", "alem", "ambas", "ambos", "antes",
        "ao", "aonde", "aos", "apos", "aquele", "aqueles",
        "as", "assim", "com", "como", "contra", "contudo",
        "cuja", "cujas", "cujo", "cujos", "da", "das", "de",
        "dela", "dele", "deles", "demais", "depois", "desde",
        "desta", "deste", "dispoe", "dispoem", "diversa",
        "diversas", "diversos", "do", "dos", "durante", "e",
        "ela", "elas", "ele", "eles", "em", "entao", "entre",
        "essa", "essas", "esse", "esses", "esta", "estas",
        "este", "estes", "ha", "isso", "isto", "logo", "mais",
        "mas", "mediante", "menos", "mesma", "mesmas", "mesmo",
        "mesmos", "na", "nas", "nao", "nas", "nem", "nesse", "neste",
        "nos", "o", "os", "ou", "outra", "outras", "outro", "outros",
        "pelas", "pelas", "pelo", "pelos", "perante", "pois", "por",
        "porque", "portanto", "proprio", "propios", "quais", "qual",
        "qualquer", "quando", "quanto", "que", "quem", "quer", "se",
        "seja", "sem", "sendo", "seu", "seus", "sob", "sobre", "sua",
        "suas", "tal", "tambem", "teu", "teus", "toda", "todas", "todo",
        "todos", "tua", "tuas", "tudo", "um", "uma", "umas", "uns", "para", "pra", "cu"};

    private static String[] INGLES_STOP_WORDS = {"m", "a", "about", "above", "above", "across", "after", "afterwards",
        "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "am",
        "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything",
        "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes",
        "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between",
        "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could",
        "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight",
        "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone",
        "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five",
        "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give",
        "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein",
        "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in",
        "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly",
        "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more",
        "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never",
        "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere",
        "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our",
        "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re",
        "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side",
        "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime",
        "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them",
        "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon",
        "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout",
        "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un",
        "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever",
        "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon",
        "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why",
        "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves",
        "the"};
    
    
    public static HashMap<String, String> getPortuguesStopWords(){
        HashMap<String, String> map = new HashMap<String, String>();
        for(String word : BRAZILIAN_STOP_WORDS){
            map.put(word, word);
        }
        for(String word : ALFABETO){
            map.put(word, word);
        }
        return map;
    }
    
    public static HashMap<String, String> getInglesStopWords(){
        HashMap<String, String> map = new HashMap<String, String>();
        for(String word : INGLES_STOP_WORDS){
            map.put(word, word);
        }
        for(String word : ALFABETO){
            map.put(word, word);
        }
        return map;
    }
    
    
    public static String getTextWithoutStopWords(String txt){
        txt = ApplicationUtilies.removerAcentos(txt);
        StringTokenizer tokens = new StringTokenizer(txt);
        HashMap<String, String> mapPortugues = StopWords.getPortuguesStopWords();
        HashMap<String, String> mapIngles = StopWords.getInglesStopWords();
        String txtResult = "";        
        while(tokens.hasMoreTokens()){
            String word = tokens.nextToken();            
            if(!mapPortugues.containsKey(word) && !mapIngles.containsKey(word)){
                txtResult += " " + word;
            }
        }
        return txtResult;
    }

}
