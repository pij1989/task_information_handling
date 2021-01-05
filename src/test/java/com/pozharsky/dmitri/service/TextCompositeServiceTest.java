package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.comparator.ComponentComparator;
import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.parser.impl.*;
import com.pozharsky.dmitri.reader.TextReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TextCompositeServiceTest {
    static final String DATA_FILE = "data\\text.txt";
    static final int AMOUNT = 5;
    Component composite;
    TextCompositeService service;
    Map<String, Integer> map;

    @BeforeMethod
    public void setUp() {
        TextReader reader = new TextReader();
        String data = reader.readText(DATA_FILE);
        ParagraphParser paragraphParser = new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser(new SymbolParser()))));
        composite = paragraphParser.parse(data).orElseThrow();
        service = new TextCompositeService();
        map = new HashMap<>();
        map.put("here", 2);
        map.put("fact", 2);
        map.put("be", 2);
        map.put("reader", 2);
        map.put("when", 2);
        map.put("content", 3);
        map.put("lorem", 2);
        map.put("that", 3);
        map.put("of", 5);
        map.put("has", 2);
        map.put("readable", 2);
        map.put("established", 2);
        map.put("a", 9);
        map.put("using", 2);
        map.put("b", 2);
        map.put("like", 2);
        map.put("will", 2);
        map.put("more", 2);
        map.put("its", 2);
        map.put("is", 3);
        map.put("it", 6);
        map.put("the", 5);
        map.put("layout", 2);
        map.put("with", 2);
        map.put("at", 2);
        map.put("ipsum", 3);
        map.put("looking", 2);
        map.put("page", 2);
        map.put("tostring", 2);
    }

    @AfterMethod
    public void tearDown() {
        composite = null;
        service = null;
        map = null;
    }

    @Test
    public void testSortParagraphBySentenceAmount() {
        service.sortParagraphBySentenceAmount(composite, ComponentComparator.SENTENCE_AMOUNT);
        String actual = composite.get(1).buildString();
        String expect = "Bye бандерлоги.";
        assertEquals(actual, expect);
    }

    @Test
    public void testFindSentenceWithMaxLengthWord() {
        List<Component> components = service.findSentenceWithMaxLengthWord(composite);
        String actual = components.get(0).buildString();
        String expect = "The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), " +
                "as opposed to using (Content here), content here's, making it look like readable English?";
        assertEquals(actual, expect);
    }

    @Test
    public void testRemoveSentencesAmountWordLess() {
        List<Component> components = service.removeSentencesAmountWordLess(composite, AMOUNT);
        String actual = components.get(0).buildString();
        String expect = "Bye бандерлоги.";
        assertEquals(actual, expect);
    }

    @Test
    public void testDefineAmountSameWord() {
        Map<String, Integer> actual = service.defineAmountSameWord(composite);
        Map<String, Integer> expect = map;
        assertEquals(actual, expect);
    }
}
