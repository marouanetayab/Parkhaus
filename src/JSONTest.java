import org.junit.Test;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;

public class JSONTest {

    @Test
    public void testOnlyNumbers() {
        JSON json = new JSON("{number: 1, number: 2, number: 3, number: 4}");

        assertEquals(10, json.sumValues());
    }

    @Test
    public void testOnlyStrings() {
        JSON json = new JSON("{string1: \"String 1\", string2: \"String 2\"}");

        assertEquals("String 1String 2", json.concatStrings());
    }

    @Test
    public void testNumbersAndStrings()  {
        JSON json = new JSON("{number: 1, string: \"String\", another_numer: 3, another_string: \"test\"}");
        assertEquals(4, json.sumValues());
        assertEquals("Stringtest", json.concatStrings());
    }

    @Test
    public void testEncapsulatedNumbers() {
        JSON json = new JSON("{number: 1, moreNumbers: {number: 2, number2: 4}}");
        assertEquals(7, json.sumValues());
    }

    @Test
    public void testEncapsulatedStrings() {
        JSON json = new JSON("{string: \"String\", moreStrings: {string: \"String 2\", string2: \"String 3\"}}");
        assertEquals("StringString 2String 3", json.concatStrings());
    }

    @Test
    public void testArrayNumberValues() {
        JSON json = new JSON("{array: [1, 2, 3]}");
        assertEquals(6, json.sumValues());
    }

    @Test
    public void testArrayStringValues() {
        JSON json = new JSON("{array: [\"String\", \"String\", \"String\"]}");
        assertEquals("StringStringString", json.concatStrings());
    }

    @Test
    public void testNumbersInArraysObjectsAndValues() {
        JSON json = new JSON("number: 2, object: {number2: 1, anothervalue: 3, enc_object: {string: 3}, anotherarray: [5,6]}, array: [3,6, {id: 234}]");
        assertEquals(263, json.sumValues());
    }

    @Test
    public void testStringsInArraysObjectsAndValues() {
        JSON json = new JSON("number: \"string\", object: {number2: \"2\", anothervalue: \"test3\", enc_object: {string: \"test\"}, anotherarray: [\"String\", \"String\"]}, array: [\"test\",\"tast\", {id: \"asdas\"}]");
        assertEquals("string2test3testStringStringtesttastasdas", json.concatStrings());
    }
}