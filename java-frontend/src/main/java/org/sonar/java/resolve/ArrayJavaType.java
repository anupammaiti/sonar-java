/*
 * SonarQube Java
 * Copyright (C) 2012-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.resolve;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sonar.plugins.java.api.semantic.Type;

public class ArrayJavaType extends JavaType implements Type.ArrayType {

  private final org.sonar.java.resolve.ArrayJavaType erasure;
  /**
   * Type of elements of this array.
   */
  JavaType elementType;

  /**
   * @param arrayClass {@link Symbols#arrayClass}
   */
  public ArrayJavaType(JavaType elementType, JavaSymbol.TypeJavaSymbol arrayClass) {
    super(ARRAY, arrayClass);
    this.elementType = elementType;
    // element
    this.erasure = new org.sonar.java.resolve.ArrayJavaType(arrayClass);
  }

  private ArrayJavaType(JavaSymbol.TypeJavaSymbol arrayClass) {
    super(ARRAY, arrayClass);
    this.erasure = this;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(31, 37).append(elementType).hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof org.sonar.java.resolve.ArrayJavaType)) {
      return false;
    }
    org.sonar.java.resolve.ArrayJavaType rhs = (org.sonar.java.resolve.ArrayJavaType) obj;
    return new EqualsBuilder()
      .append(elementType, rhs.elementType)
      .isEquals();
  }

  @Override
  public String toString() {
    return elementType.toString() + "[]";
  }

  @Override
  public JavaType elementType() {
    return elementType;
  }

  @Override
  public JavaType erasure() {
    if (erasure.elementType == null) {
      erasure.elementType = elementType.erasure();
    }
    return erasure;
  }
}
