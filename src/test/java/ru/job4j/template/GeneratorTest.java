package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.NoSuchElementException;


public class GeneratorTest {

    @Test
    public void test() throws NoSuchElementException {
        Gen gen = new Gen();
        String text = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = gen.trimTemplate(text);
        gen.checkKeyAndVal(map);
        gen.produce(gen.finalString(text), gen.getKeyAndVal(map));
    }

    @Test
    public void whenEmpty() throws NoSuchElementException {
        Gen gen = new Gen();
        String text = null;
        Map<String, String> map = gen.trimTemplate(text);
        gen.produce(gen.finalString(text), gen.getKeyAndVal(map));
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongKeyAndVal() {
        Gen gen = new Gen();
        String text = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = gen.trimTemplate(text);
        gen.checkKeyAndVal(map);
    }

}