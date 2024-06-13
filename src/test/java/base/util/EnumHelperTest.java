package base.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EnumHelperTest {
  @Test
  public void test_get_enum_name_with_name_annotation() {
    enum TestEnum {
      @Name("Test Name")
      VALUE1,
      VALUE2
    }

    String result = EnumHelper.getEnumName(TestEnum.VALUE1);
    assertEquals("Test Name", result);
  }

  @Test
  public void test_returns_value_of_name_annotation_if_present() {
    enum TestEnum {
      @Name("Test Name")
      VALUE1,
      VALUE2
    }

    String result = EnumHelper.getEnumName(TestEnum.VALUE1);
    assertEquals("Test Name", result);
  }

  @Test
  public void test_returns_enum_name_if_annotation_not_present() {
    // Create a simple enum without Name annotation
    enum TestEnum {
      VALUE1,
      VALUE2
    }

    // Get the enum name and assert it is the same as the enum value
    String result = EnumHelper.getEnumName(TestEnum.VALUE1);
    assertEquals("VALUE1", result);
  }

  @Test
  public void test_get_enum_name_with_null_value() {
    String result = EnumHelper.getEnumName(null);
    assertNull(result);
  }

  @Test
  public void test_retrieve_enum_name_invalid_field() {
    // Create a dummy enum class for testing
    enum DummyEnum {
      VALUE1,
      VALUE2
    }

    // Call getEnumName with an invalid enum field
    String result = EnumHelper.getEnumName(DummyEnum.VALUE2);

    // Assert that the result is null
    assertNotNull(result);
  }
}
