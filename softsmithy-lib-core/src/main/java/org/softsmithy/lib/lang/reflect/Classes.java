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
package org.softsmithy.lib.lang.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A utility class for Classes.
 *
 * @author Florian Brunner
 * @see java.lang.Class
 */
public final class Classes {

    private static final Map<Class<?>, Class<?>> WRAPPER_CLASSES;

    static {
        Map<Class<?>, Class<?>> wrappers = new HashMap<>();
        wrappers.put(Boolean.TYPE, Boolean.class);
        wrappers.put(Character.TYPE, Character.class);
        wrappers.put(Byte.TYPE, Byte.class);
        wrappers.put(Short.TYPE, Short.class);
        wrappers.put(Integer.TYPE, Integer.class);
        wrappers.put(Long.TYPE, Long.class);
        wrappers.put(Float.TYPE, Float.class);
        wrappers.put(Double.TYPE, Double.class);
        wrappers.put(Void.TYPE, Void.class);
        WRAPPER_CLASSES = Collections.unmodifiableMap(wrappers);
    }

    /**
     * No public constructor.
     */
    private Classes() {
    }

    /**
     * Tests if the specified class implements (directly or indirectly) the specified interface.
     *
     * @param aClass the specified class
     * @param anInterface the specified interface
     * @return true if the specified class implements (directly or indirectly) the specified interface; false otherwise
     * @exception IllegalArgumentException if the parameter anInterface is not an interface
     */
    public static boolean implementsInterface(Class<?> aClass, Class<?> anInterface) {
        if (!anInterface.isInterface()) {
            throw new IllegalArgumentException(anInterface.getName() + " is not an interface!");
        }
        boolean impl = false;
        Class<?> current = aClass;
        while ((current != null) && (!impl)) {
            impl = Arrays.asList(current.getInterfaces()).contains(anInterface);
            current = current.getSuperclass();
        }
        return impl;
    }

    /**
     * Tests if the first class extends (directly or indirectly) the second class.
     *
     * @param subclass the subclass
     * @param superclass the superclass
     * @return true if subclass extends (directly or indirectly) the superclass; false otherwise
     */
    public static boolean extendsClass(Class<?> subclass, Class<?> superclass) {
        boolean ext = false;
        for (Class<?> current = subclass.getSuperclass(); (current != null) && (!ext); current = current.getSuperclass()) {
            ext = current.equals(superclass);

        }
        return ext;
    }

    public static Class<?> getTopMostCommonClass(Class<?> a, Class<?> b) {
        Class<?> baseClass;
        if (a == null || b == null) {
            baseClass = null;
        } else if (a.equals(b)) {
            baseClass = a;
        } else if (extendsClass(a, b)) {
            baseClass = b;
        } else if (extendsClass(b, a)) {
            baseClass = a;
        } else {
            baseClass = getTopMostCommonClass(a.getSuperclass(), b.getSuperclass());
        }
        return baseClass;
    }

    public static Class<?> getTopMostCommonClass(Class<?>[] classes) {
        Class<?> topMostCommonClass = null;
        if (classes.length > 0) {
            topMostCommonClass = classes[0];
            for (int i = 1; i < classes.length; i++) {
                topMostCommonClass = getTopMostCommonClass(topMostCommonClass, classes[i]);
            }
        }
        return topMostCommonClass;
    }

    public static Class<?>[] getClasses(Object[] objects) {
        Class<?>[] classes = new Class<?>[objects.length];
        for (int i = 0; i < objects.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }

    /**
     * Gets all types (class, super classes, interfaces and super interfaces) of the provided object.
     *
     * @param obj the object
     * @return all types (class, super classes, interfaces and super interfaces) of the provided object
     */
    public static Set<Class<?>> getTypes(Object obj) {
        Set<Class<?>> types = new HashSet<>();
        for (Class<?> type = obj.getClass(); type != null; type = type.getSuperclass()) {
            types.add(type);
            addInterfaces(types, type.getInterfaces());
        }
        return types;
    }

    private static void addInterfaces(Set<Class<?>> types, Class<?>[] interfaces) {
        for (Class<?> anInterface : interfaces) {
            types.add(anInterface);
            // recursion
            addInterfaces(types, anInterface.getInterfaces());
        }
    }

    public static Class<?>[] getClasses(Collection<?> objects) {
        Class<?>[] classes = new Class<?>[objects.size()];
        Iterator<?> iterator = objects.iterator();
        for (int i = 0; i < classes.length; i++) {
            classes[i] = iterator.next().getClass();
        }
        return classes;
    }

    public static Class<?> getWrapperClass(Class<?> primitiveClass) {
        if (!primitiveClass.isPrimitive()) {
            throw new IllegalArgumentException("primitiveClass must be a primitive class!");
        }
        return WRAPPER_CLASSES.get(primitiveClass);
    }

    public static boolean containsMethod(Class<?> aClass, String name, Class<?>[] parameterTypes) {
        boolean containsMethod = false;
        for (Method method : aClass.getMethods()) {
            if (method.getName().equals(name)) {
                containsMethod = Arrays.equals(method.getParameterTypes(), parameterTypes);
            }
            if (containsMethod) {
                break;
            }
        }
        return containsMethod;
    }

    /**
     * Casts a wildcard class object to its generic type.
     *
     * @param <T> the type of the class object
     * @param type the wildcard class object
     * @return the generic class type
     */
    public static <T> Class<T> cast(Class<?> type) {
        return (Class<T>) type;
    }

}
