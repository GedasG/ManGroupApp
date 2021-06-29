package lt.gg;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testSharesCharacters() {

        assertTrue(Utility.sharesCharacters("one", "two"));
        assertTrue(Utility.sharesCharacters("abcc", "qwea"));
        assertTrue(Utility.sharesCharacters("abcc", "bfd"));

        assertFalse(Utility.sharesCharacters("a", ""));
        assertFalse(Utility.sharesCharacters(null, "aaaaaaaaaaaaaawwwwwwwwwwwwwwwwwww"));
        assertFalse(Utility.sharesCharacters("asdfg", "qwe"));
    }

    @Test
    public void testScoreStrings() {

        assertSame(0, Utility.scoreStrings("one", "two"));
        assertSame(0, Utility.scoreStrings("abcc", "qwea"));
        assertSame(0, Utility.scoreStrings("abcc", "bfd"));
        assertSame(0, Utility.scoreStrings("cat", "mat"));

        assertSame(0, Utility.scoreStrings("a", ""));
        assertSame(0, Utility.scoreStrings(null, "aaaaaaaaaaaaaawwwwwwwwwwwwwwwwwww"));
        assertSame(15, Utility.scoreStrings("asdfg", "qwe"));
        assertSame(9, Utility.scoreStrings("cat", "dog"));
    }

    @Test
    public void testMaxWordPair() {

        List<String> exampleList = Arrays.asList("John","Bob","Charles","Trev","one","two","two","two","abcc","qwe","qwe","qwe","abcc","bfd","bfd","bfd","bfd","cat","cat","cat","mat","mat","dog");

        Pair<String, String> returnPair = Utility.maxWordPair(exampleList);
        int score = Utility.scoreStrings(returnPair.getKey(), returnPair.getValue());
        assertEquals(21, score);
        assertEquals(new Pair<>("Bob", "Charles"), returnPair);
    }

    @Test
    public void testMaxWordPairTemp() {

        List<String> exampleList = Arrays.asList("John","Bob",null,"Charless","Trev","one",null,null,"two","two","two","abcc","qwe",null,"qwe","qwe",null,null,"abcc","bfd","bfd","bfd","bfd","cat","cat","cat","","","","mat","mat",null,"dog");

        Pair<String, String> returnPair = Utility.maxWordPair(exampleList);
        int score = Utility.scoreStrings(returnPair.getKey(), returnPair.getValue());
        assertEquals(24, score);
        assertEquals(new Pair<>("Bob", "Charless"), returnPair);
    }

    @Test
    public void testRemoveElements() {

        List<String> exampleList = Arrays.asList("John","Bob",null,"Charless","Trev","one","two","two","two","abcc","qwe",null,"qwe","qwe",null,null,"abcc","bfd","bfd","bfd","bfd","cat","cat","cat","","","","mat","mat",null,"dog");

        List<Integer> indices = Arrays.asList(2, 4, 8);

        List<String> modifiedList = Utility.removeElements(exampleList,indices);
        assertEquals(exampleList.size() - 3, modifiedList.size());
        List<String> testList = new ArrayList<>(exampleList);
        testList.remove(8);
        testList.remove(4);
        testList.remove(2);
        assertEquals(testList, modifiedList);
        modifiedList.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testRemoveElementPairs() {

        List<List<String>> exampleList = Arrays.asList(
            Arrays.asList("John", "Bob", "Charless"),
            Arrays.asList("Trev", "Geoff"),
            Arrays.asList("Bart", "Sue")
        );

        List<List<Integer>> indices = Arrays.asList(
            Arrays.asList(0, 0),
            Arrays.asList(0, 2),
            Arrays.asList(1, 1)
        );

        List<List<String>> modifiedList = Utility.removeElementPairs(exampleList,indices);
        modifiedList.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testRemoveElementPairsN() {

        List<List<String>> exampleList = Arrays.asList(
            Arrays.asList("John", "Bob", "Charless"),
            Arrays.asList("Trev", "Geoff"),
            Arrays.asList("Bart", "Sue")
        );

        List<List<Integer>> indices = Arrays.asList(
            Arrays.asList(0, 0),
            Arrays.asList(0, 2),
            Arrays.asList(1, 1)
        );

        List<List<String>> modifiedList = (List<List<String>>) Utility.removeElementPairsN(exampleList,indices);
        modifiedList.stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void testRemoveElementPairsN3() {

        List<List<List<String>>> exampleList = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("John","Bob","Charless"),
                        Arrays.asList("TestMan","Hiro","Random"),
                        Arrays.asList("TestWoman","Anna","Ditto" )
                ),
                Arrays.asList(
                        Arrays.asList("Trev", "Geoff"),
                        Arrays.asList("Bart", "Sue")
                )
        );

        List<List<Integer>> indices = Arrays.asList(
                Arrays.asList(0, 0, 0),
                Arrays.asList(0, 2, 1),
                Arrays.asList(1, 1, 0)
        );

        List<List<List<String>>> expectedList = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("Bob","Charless"),
                        Arrays.asList("TestMan","Hiro","Random"),
                        Arrays.asList("TestWoman","Ditto" )
                ),
                Arrays.asList(
                        Arrays.asList("Trev", "Geoff"),
                        Arrays.asList("Sue")
                )
        );

        List<List<List<String>>> modifiedList = (List<List<List<String>>>) Utility.removeElementPairsN(exampleList,indices);
        System.out.println("------------------------------RESULT");
        modifiedList.stream().forEach(s -> System.out.println(s));
        assertEquals(expectedList, modifiedList);
    }

    @Test
    public void testRemoveElementPairsN4() {

        List<List<List<List<String>>>> exampleList = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(
                            Arrays.asList("John","Bob","Charless"),
                            Arrays.asList("TestMan","Hiro","Random"),
                            Arrays.asList("TestWoman","Anna","Ditto" )
                        ),
                        Arrays.asList(
                                Arrays.asList("AAA","BBB"),
                                Arrays.asList("CCC","DDD","EEE"),
                                Arrays.asList("FFF","GGG","HHH" )
                        ),
                        Arrays.asList(
                                Arrays.asList("NNN","MMM"),
                                Arrays.asList("OOO","PPP","RRR"),
                                Arrays.asList("SSS","TTT","XXX" )
                        )
                ),
                Arrays.asList(
                        Arrays.asList(
                            Arrays.asList("Trev", "Geoff"),
                            Arrays.asList("Bart", "Sue")
                        ),
                        Arrays.asList(
                                Arrays.asList("JJJ", "KKK"),
                                Arrays.asList("LLL", "MMM")
                        )
                )
        );

        List<List<Integer>> indices = Arrays.asList(
                Arrays.asList(0, 0, 0, 0),
                Arrays.asList(0, 2, 1, 1),
                Arrays.asList(1, 1, 0, 1)
        );

        List<List<List<List<String>>>> expectedList = Arrays.asList(
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("Bob","Charless"),
                                Arrays.asList("TestMan","Hiro","Random"),
                                Arrays.asList("TestWoman","Anna","Ditto" )
                        ),
                        Arrays.asList(
                                Arrays.asList("AAA","BBB"),
                                Arrays.asList("CCC","DDD","EEE"),
                                Arrays.asList("FFF","GGG","HHH" )
                        ),
                        Arrays.asList(
                                Arrays.asList("NNN","MMM"),
                                Arrays.asList("OOO","RRR"),
                                Arrays.asList("SSS","TTT","XXX" )
                        )
                ),
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("Trev", "Geoff"),
                                Arrays.asList("Bart", "Sue")
                        ),
                        Arrays.asList(
                                Arrays.asList("JJJ"),
                                Arrays.asList("LLL", "MMM")
                        )
                )
        );

        List<List<List<String>>> modifiedList = (List<List<List<String>>>) Utility.removeElementPairsN(exampleList,indices);
        System.out.println("------------------------------RESULT");
        modifiedList.stream().forEach(s -> System.out.println(s));
        assertEquals(expectedList, modifiedList);
    }


}
