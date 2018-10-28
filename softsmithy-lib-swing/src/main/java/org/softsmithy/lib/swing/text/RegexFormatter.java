/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

package org.softsmithy.lib.swing.text;

import java.text.*;
import java.util.regex.*;
import javax.swing.text.*;

/**
 * A regular expression based implementation of <code>AbstractFormatter</code>.
 * From:
 * http://java.sun.com/products/jfc/tsc/articles/reftf/
 * Note: requires v1.4
 * Looks interessting but not yet used.
 */
public class RegexFormatter extends DefaultFormatter {
    private Pattern pattern;
    private Matcher matcher;

    public RegexFormatter() {
        super();
    }

    /**
     * Creates a regular expression based <code>AbstractFormatter</code>.
     * <code>pattern</code> specifies the regular expression that will
     * be used to determine if a value is legal.
     * @param pattern the regex pattern
     */
    public RegexFormatter(String pattern) throws PatternSyntaxException {
        this();
        setPattern(Pattern.compile(pattern));
    }

   /**
     * Creates a regular expression based <code>AbstractFormatter</code>.
     * <code>pattern</code> specifies the regular expression that will
     * be used to determine if a value is legal.
     * @param pattern the regex pattern
     */
    public RegexFormatter(Pattern pattern) {
        this();
        setPattern(pattern);
   }

    /**
     * Sets the pattern that will be used to determine if a value is
     * legal.
     * @param pattern the regex pattern
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Returns the <code>Pattern</code> used to determine if a value is
     * legal.
     * @return the regex pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Sets the <code>Matcher</code> used in the most recent test
     * if a value is legal.
     * @param matcher the regex matcher
     */
    protected void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    /**
     * Returns the <code>Matcher</code> from the most test.
     * @return the regex matcher
     */
    protected Matcher getMatcher() {
        return matcher;
    }

    /**
     * Parses <code>text</code> returning an arbitrary Object. Some
     * formatters may return null.
     * <p>
     * If a <code>Pattern</code> has been specified and the text
     * completely matches the regular expression this will invoke
     * <code>setMatcher</code>.
     *
     * @throws ParseException if there is an error in the conversion
     * @param text String to convert
     * @return Object representation of text
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        Pattern pattern = getPattern();

        if (pattern != null) {
            Matcher matcher = pattern.matcher(text);

            if (matcher.matches()) {
                setMatcher(matcher);
                return super.stringToValue(text);
            }
            throw new ParseException("Pattern did not match", 0);
        }
        return text;
    }
}
