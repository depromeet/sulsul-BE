package com.depromeet.sulsul.oauth2;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CustomOAuth2User implements OAuth2User {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private final Set<GrantedAuthority> authorities;

  private final Map<String, Object> attributes;

  private final String nameAttributeKey;

  private Long memberId;

  private String email;
  public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
                           String nameAttributeKey, Long memberId, String email) {
    Assert.notEmpty(attributes, "attributes cannot be empty");
    Assert.hasText(nameAttributeKey, "nameAttributeKey cannot be empty");
    if (!attributes.containsKey(nameAttributeKey)) {
      throw new IllegalArgumentException("Missing attribute '" + nameAttributeKey + "' in attributes");
    }
    this.authorities = (authorities != null)
        ? Collections.unmodifiableSet(new LinkedHashSet<>(this.sortAuthorities(authorities)))
        : Collections.unmodifiableSet(new LinkedHashSet<>(AuthorityUtils.NO_AUTHORITIES));
    this.attributes = Collections.unmodifiableMap(new LinkedHashMap<>(attributes));
    this.nameAttributeKey = nameAttributeKey;
    this.memberId = memberId;
    this.email = email;
  }

  @Override
  public String getName() {
    return this.getAttribute(this.nameAttributeKey).toString();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  private Set<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
    SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
        Comparator.comparing(GrantedAuthority::getAuthority));
    sortedAuthorities.addAll(authorities);
    return sortedAuthorities;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }
    DefaultOAuth2User that = (DefaultOAuth2User) obj;
    if (!this.getName().equals(that.getName())) {
      return false;
    }
    if (!this.getAuthorities().equals(that.getAuthorities())) {
      return false;
    }
    return this.getAttributes().equals(that.getAttributes());
  }

  @Override
  public int hashCode() {
    int result = this.getName().hashCode();
    result = 31 * result + this.getAuthorities().hashCode();
    result = 31 * result + this.getAttributes().hashCode();
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: [");
    sb.append(this.getName());
    sb.append("], Granted Authorities: [");
    sb.append(getAuthorities());
    sb.append("], User Attributes: [");
    sb.append(getAttributes());
    sb.append("]");
    return sb.toString();
  }

  public Long getMemberId() {
    return memberId;
  }

  public void setMemberId(Long memberId){
    this.memberId = memberId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
