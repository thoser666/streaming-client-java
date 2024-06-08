package base.util;

import java.util.Map;

public class DictionaryExtensions {
  public static <K, V> V getOrDefault(Map<K, V> dictionary, K key) {
    if (dictionary.containsKey(key)) {
      return dictionary.get(key);
    }
    return null; // Java hat keinen direkten Äquivalent zum "default(V)" in C#
  }
}
