package com.depromeet.sulsul.util;

import com.depromeet.sulsul.common.dto.EnumModel;
import com.depromeet.sulsul.common.dto.EnumValue;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtil {

  public static final String EMPTY_STRING = "";

  public static boolean isEmpty(String str) {
    return StringUtils.isBlank(str);
  }

  public static List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
    return Arrays.stream(e.getEnumConstants()).map(EnumValue::new).collect(Collectors.toList());
  }

  public static EnumValue toEnumValue(EnumModel enumModel) {
    return new EnumValue(enumModel);
  }
}
