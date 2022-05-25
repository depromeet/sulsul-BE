package com.depromeet.sulsul.util;

import com.depromeet.sulsul.common.dto.EnumModel;
import com.depromeet.sulsul.common.dto.EnumValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtil {

  public static final String EMPTY_STRING = "";

  public static boolean isEmpty(String str) {
    return str == null || str.trim().isEmpty();
  }

  public static List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
    return Arrays
        .stream(e.getEnumConstants())
        .map(EnumValue::new)
        .collect(Collectors.toList());
  }

  public static EnumValue toEnumValue(EnumModel enumModel) {
    return new EnumValue(enumModel);
  }
}
