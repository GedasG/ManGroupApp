package lt.gg;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Utility {

    //Returns true if strings share at least one character
    static boolean sharesCharacters(String one, String two) {

        //return false in case of null/empty strings as it will never share any characters
        if (one == null || two == null || one.isEmpty() || two.isEmpty()) {
            return false;
        }

        //distinct values (upper/lower case letters - are different characters)
        Set<Integer> setOne = one.chars()
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> setTwo = two.chars()
                .boxed()
                .collect(Collectors.toSet());

        //will check collections (disjoin returns true if collections don't share any elements)
        return !Collections.disjoint(setOne, setTwo);

    }

    //Returns "score" (returns product of their lengths if the strings don't share any characters; otherwise returns 0)
    static int scoreStrings(String one, String two) {
        if (sharesCharacters(one, two)) {
            return 0;
        }
        else if (one == null || two == null || one.isEmpty() || two.isEmpty()) {
            //null/empty strings would also score 0
            return 0;
        } else {
            return one.length() * two.length();
        }
    }

   //Returns a pair of Strings with the highest scores from the list
   static Pair<String, String> maxWordPair(List<String> words) {

        //null, 0 length list
        if (words == null || words.isEmpty()) {
            return null;
        }

        int highestScore = 0;
        int tmpScore = 0;
        String stringOne = words.get(0);
        String stringTwo = words.get(0);

       //should not check same strings???
       //distinct values, remove null and empty strings
       words = words.stream().distinct().filter(s -> s != null && !s.isEmpty()).collect(Collectors.toList());

       for (int i = 0; i < words.size(); i++) {
           for (int j = 0; j < words.size(); j++) {
               if (i == j) {
                   //no need to try to score as it would be 0
                   continue;
               } else {
                   tmpScore = scoreStrings(words.get(i), words.get(j));
                   if (tmpScore > highestScore) {
                       highestScore = tmpScore;
                       stringOne = words.get(i);
                       stringTwo = words.get(j);
                   }
               }
           }
       }

       Pair<String, String> pair = new Pair<>(stringOne, stringTwo);
       return pair;
    }

    //Part 1:
    //Write a function that will remove a given collection of indices from a list.
    static List<String> removeElements(List<String> list, List<Integer> indices) {
        //make a copy
        List<String> returnList = new ArrayList<>(list);

        //assume indices are sorted (otherwise need to sort it first), like:
        //TreeSet<Integer> sortedIndices = new TreeSet<Integer>(indices);
        //Iterator<Integer> iterator = sortedIndices.descendingIterator();

        ListIterator<Integer> iterator = indices.listIterator(indices.size());

        //loop indices from the end so to keep the correct indices
        while(iterator.hasPrevious()) {
            int index = iterator.previous();
            returnList.remove(index);
            //potentially could make it more efficient if we processed list in one go???
        }

        return returnList;
    }

    //Part 2:
    //Write a function that will remove a given collection of index pairs from a list of lists.
    static List<List<String>> removeElementPairs(List<List<String>> list, List<List<Integer>> indices) {
        //make a copy
        List<List<String>> returnList = new ArrayList<>(list);

        //assume indices are sorted (otherwise need to sort it first)
        ListIterator<List<Integer>> iterator = indices.listIterator(indices.size());

        //loop indices from the end so to keep the correct indices
        while(iterator.hasPrevious()) {
            List<Integer> index = iterator.previous();

            returnList.get(index.get(0)).remove(index.get(1).intValue());
            //potentially could make it more efficient if we processed list in one go/groups???
        }

        return returnList;
    }

    //Part 3:
    //Write a function that will remove a given collection of index n-tuples from a n-deep nested list of lists.
    static List<?> removeElementPairsN(List<?> list, List<?> indices) {

        System.out.println("----------------------");
        System.out.println("LIST:" + list);
        System.out.println("INDICES: " + indices);
        //T could be a String or another List

        if (list.get(0) instanceof String) {
            System.out.println("Instance of String, simple remove");
            List<String> returnList = removeElements((List<String>) list, (List<Integer>) indices);
            return (List<?>) returnList;
        } else if (list.get(0) instanceof List<?>) {
            System.out.println("Instance of List");
            List<List<?>> castedList = (List<List<?>>) list;
            List<List<Integer>> castedIndices = (List<List<Integer>>) indices;

            ListIterator<List<?>> iterator = castedList.listIterator(castedList.size());

            while(iterator.hasPrevious()) {
                int index = iterator.previousIndex();
                List<?> tmpList = iterator.previous();

                //collect indices for specific list
                List<List<Integer>> tmpIndices = new ArrayList<>();
                for (List<Integer> i : castedIndices) {
                    if (i.get(0) == index) {
                        tmpIndices.add(i);
                    }
                }

                //if there are indices to be removed
                if (tmpIndices.size() > 0) {

                    //if indices size = 0, that means that indices can be put into simple List<Integer>
                    if (tmpIndices.get(0).size() == 2) {
                        List<Integer> indice = tmpIndices.stream().map(i -> i.get(1)).collect(Collectors.toList());

                        //recursive call
                        tmpList = removeElementPairsN(tmpList, indice);
                    } else {
                        //Remove first element from indices
                        for (int i = 0; i < tmpIndices.size(); i++) {
                            tmpIndices.set(i, tmpIndices.get(i).stream().skip(1).collect(Collectors.toList()));
                        }
                        //recursive call
                        tmpList = removeElementPairsN(tmpList, tmpIndices);
                    }
                    //update list
                    castedList.set(index, tmpList);
                }

            }

            return castedList;
        } else {
            //something else completely, shouldnt ever be the case
            System.out.println("Impossible scenario");
            return null;
        }

    }

}
