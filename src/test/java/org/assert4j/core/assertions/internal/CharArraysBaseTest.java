/*
 * Created on Oct 19, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2011 the original author or authors.
 */
package org.assert4j.core.assertions.internal;

import static org.assert4j.core.assertions.test.CharArrays.arrayOf;
import static org.assert4j.core.assertions.test.ExpectedException.none;

import static org.mockito.Mockito.spy;

import java.util.Comparator;

import org.assert4j.core.assertions.internal.CharArrays;
import org.assert4j.core.assertions.internal.ComparatorBasedComparisonStrategy;
import org.assert4j.core.assertions.internal.Failures;
import org.assert4j.core.assertions.internal.StandardComparisonStrategy;
import org.assert4j.core.assertions.test.ExpectedException;
import org.assert4j.core.assertions.util.CaseInsensitiveCharacterComparator;
import org.junit.Before;
import org.junit.Rule;


/**
 * Base class for testing <code>{@link CharArrays}</code>, set up an instance with {@link StandardComparisonStrategy} and another
 * with {@link ComparatorBasedComparisonStrategy}.
 * <p>
 * Is in <code>org.fest.assertions.internal</code> package to be able to set {@link CharArrays#failures} appropriately.
 * 
 * @author Joel Costigliola
 */
public class CharArraysBaseTest {

  @Rule
  public ExpectedException thrown = none();

  /**
   * is initialized with {@link #initActualArray()} with default value = {'a', 'b', 'c'}
   */
  protected char[] actual;
  protected Failures failures;
  protected CharArrays arrays;

  protected ComparatorBasedComparisonStrategy caseInsensitiveComparisonStrategy;
  protected CharArrays arraysWithCustomComparisonStrategy;

  private CaseInsensitiveCharacterComparator caseInsensitiveComparator = new CaseInsensitiveCharacterComparator();

  @Before
  public void setUp() {
    failures = spy(new Failures());
    arrays = new CharArrays();
    arrays.failures = failures;
    caseInsensitiveComparisonStrategy = new ComparatorBasedComparisonStrategy(comparatorForCustomComparisonStrategy());
    arraysWithCustomComparisonStrategy = new CharArrays(caseInsensitiveComparisonStrategy);
    arraysWithCustomComparisonStrategy.failures = failures;
    initActualArray();
  }

  protected void initActualArray() {
    actual = arrayOf('a', 'b', 'c');
  }

  protected Comparator<?> comparatorForCustomComparisonStrategy() {
    return caseInsensitiveComparator;
  }

}