package base.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryExtensionsTest {
    @Test
    void test_returns_value_when_key_exists_in_dictionary() {
        Map<String, String> dictionary = Map.of("key1", "value1", "key2", "value2");
        String result = DictionaryExtensions.getOrDefault(dictionary, "key1");
        assertEquals("value1", result);
    }

    @Test
    void test_handles_empty_dictionary_input() {
        Map<String, String> dictionary = Map.of();
        String result = DictionaryExtensions.getOrDefault(dictionary, "key1");
        assertNull(result);
    }

}