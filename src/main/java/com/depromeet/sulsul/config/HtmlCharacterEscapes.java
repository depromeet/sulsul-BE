package com.depromeet.sulsul.config;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import org.apache.commons.lang3.StringEscapeUtils;

public class HtmlCharacterEscapes extends CharacterEscapes {
  private final int[] asciiEscapes;

  public HtmlCharacterEscapes() {
    // 1. XSS 방지 처리할 특수 문자 지정
    asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
    asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
    asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
  }

  @Override
  public int[] getEscapeCodesForAscii() {
    return asciiEscapes;
  }

  @Override
  public SerializableString getEscapeSequence(int ch) {
    SerializedString serializedString = null;
    char charAt = (char) ch;
    //emoji jackson parse 오류에 따른 예외 처리
    if (Character.isHighSurrogate(charAt) || Character.isLowSurrogate(charAt)) {
      StringBuilder sb = new StringBuilder();
      sb.append("\\u");
      sb.append(String.format("%04x",ch));
      serializedString = new SerializedString(sb.toString());
    } else {
      serializedString = new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString(charAt)));
    }
    return serializedString;
  }
}