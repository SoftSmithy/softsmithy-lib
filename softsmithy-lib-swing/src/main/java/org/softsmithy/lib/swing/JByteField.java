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
package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A byte field.
 *
 * @author puce
 */
public class JByteField extends JWholeNumberField {

    /**
     * The default value.
     */
    private static final byte DEFAULT_VALUE = 0;

    /**
     * The default minimum value.
     */
    private static final byte DEFAULT_MIN_VALUE = Byte.MIN_VALUE;

    /**
     * The default maximum value.
     */
    private static final byte DEFAULT_MAX_VALUE = Byte.MAX_VALUE;

    /**
     * Creates a new instance of this class.
     */
    public JByteField() {
        this(Locale.getDefault());
    }

    /**
     * Creates a new instance of this class.
     *
     * @param locale the locale
     */
    public JByteField(Locale locale) {
        this(DEFAULT_VALUE, locale);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     */
    public JByteField(byte value) {
        this(value, Locale.getDefault());
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     * @param locale the locale
     */
    public JByteField(byte value, Locale locale) {
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JByteField(byte minValue, byte maxValue) {
        this(minValue, maxValue, Locale.getDefault());
    }

    /**
     * Creates a new instance of this class.
     *
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JByteField(byte minValue, byte maxValue, Locale locale) {
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JByteField(byte value, byte minValue, byte maxValue) {
        this(value, minValue, maxValue, Locale.getDefault());
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JByteField(byte value, byte minValue, byte maxValue, Locale locale) {
        super(new NumberFormatterFactory<>(ByteFormatter.class, new ByteFormatter()));
        setMinimumByteValue(minValue);
        setMaximumByteValue(maxValue);
        setByteValue(value);
        setLocale(locale);
    }

    /**
     * Gets the value.
     *
     * @return the value.
     */
    public byte getByteValue() {
        return getNumberValue().byteValue();
    }

    /**
     * Sets the value.
     *
     * @param i the value
     */
    public void setByteValue(byte i) {
        setNumberValue(BigInteger.valueOf(i));
    }

    /**
     * Gets the minimum value.
     *
     * @return the minimum value
     */
    public byte getMinimumByteValue() {
        return getMinimumNumberValue().byteValue();
    }

    /**
     * Sets the minimum value.
     *
     * @param minByteValue the minimum value
     */
    public void setMinimumByteValue(byte minByteValue) {
        setMinimumNumberValue(BigInteger.valueOf(minByteValue));
    }

    /**
     * Gets the maximum value.
     *
     * @return the maximum value
     */
    public byte getMaximumByteValue() {
        return getMaximumNumberValue().byteValue();
    }

    /**
     * Sets the maximum value.
     *
     * @param maxByteValue the maximum value
     */
    public void setMaximumByteValue(byte maxByteValue) {
        setMaximumNumberValue(BigInteger.valueOf(maxByteValue));
    }

    /**
     * Sets the formatter. Must be an instance of ByteFormatter. You should not normally invoke this. See the documentation of the base class for more information.
     *
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (!(formatter instanceof ByteFormatter)) {
            throw new IllegalArgumentException("formatter must be an instance of ByteFormatter!");
        }
        super.setFormatter(formatter);
    }

    /**
     * Gets the number formatter.
     *
     * @return the number formatter
     */
    public ByteFormatter getByteFormatter() {
        return (ByteFormatter) getAbstractXNumberFormatter();
    }

}
