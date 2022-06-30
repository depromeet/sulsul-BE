package com.depromeet.sulsul.util;

import com.depromeet.sulsul.common.dto.EnumModel;
import com.depromeet.sulsul.common.dto.EnumValue;
import com.depromeet.sulsul.oauth2.User;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtil {

  public static final String EMPTY_STRING = "";
  public static final String DEFAULT_START_COUNTRY_KOR = "대한민국";
  public static final String DEFAULT_START_COUNTRY_ENG = "KOR";
  public static final int ZERO = 0;
  public static final int ONE = 1;
  public static final int TWO = 2;

  public static boolean isEmpty(String str) {
    return StringUtils.isBlank(str);
  }

  public static List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
    return Arrays.stream(e.getEnumConstants()).map(EnumValue::new).collect(Collectors.toList());
  }

  public static EnumValue toEnumValue(EnumModel enumModel) {
    return new EnumValue(enumModel);
  }

  public static long getMemberIdFromPrincipal(Authentication authentication) {
    return Long.parseUnsignedLong(((User) authentication.getPrincipal()).getUsername());
  }
}
