/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (GitHub user: puce77). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.lang.model.element;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.type.TypeMirror;

/**
 * A utility class for Java model elements.
 *
 * @author puce
 */
public final class ModelElementUtils {

    private ModelElementUtils() {
    }

    /**
     * Gets the class names of an annotation member of type {@link Class}-array.
     *
     * <p>
     * Given:
     * <p>
     * {@code @Foo(barClasses={Bar1.class, Bar2.class})}
     * <p>
     * then:
     * <p>
     * {@code getClassNamesOfClassArrayAnnotationValue(annotationMirrors, Foo.class, "barClasses")}
     * <p>
     * returns:
     * <p>
     * ["package1.Bar1", "package2.Bar2"]
     *
     * @param annotationMirrors the list of annotationMirrors including an annotationMirror for the annotation in question
     * @param annotationClass the annotation class
     * @param memberName the name of the annotation member of type {@link Class}-array
     * @return the class names of an annotation member of type {@link Class}-array.
     */
    public static List<String> getClassNamesOfClassArrayAnnotationValue(List<? extends AnnotationMirror> annotationMirrors, Class<? extends Annotation> annotationClass, String memberName) {
        return annotationMirrors.stream()
                .filter(annotationMirror -> represents(annotationMirror, annotationClass))
                .map(AnnotationMirror::getElementValues)
                .flatMap(map -> map.entrySet().stream())
                .filter(elementValueEntry -> elementValueEntry.getKey().getSimpleName().contentEquals(memberName))
                .map(Map.Entry::getValue)
                .map(ModelElementUtils::getArrayAnnotationValues) // expected value type is an array -> List<? extends AnnotationValue>
                .flatMap(List::stream)
                .map(ModelElementUtils::getClassAnnotationValue) // the expected type of the array is Class -> TypeMirror
                .map(TypeMirror::toString)
                .collect(Collectors.toList());
    }

    /**
     * Checks the annotationMirror represents an "instance" of the given annotation.
     *
     * @param annotationMirror
     * @param annotationClass
     * @return
     */
    private static boolean represents(AnnotationMirror annotationMirror, Class<? extends Annotation> annotationClass) {
        return annotationMirror.getAnnotationType().toString().equals(annotationClass.getName());
    }

    /**
     * Gets the values of an annotation member of type array.
     *
     * @param annotationValue
     * @return
     */
    private static List<? extends AnnotationValue> getArrayAnnotationValues(AnnotationValue annotationValue) {
        return (List<? extends AnnotationValue>) annotationValue.getValue();
    }

    /**
     * Gets the value of an anntation value, which is expected to represent a Class type -> TypeMirror.
     *
     * @param annotationValue
     * @return
     */
    private static TypeMirror getClassAnnotationValue(AnnotationValue annotationValue) {
        return (TypeMirror) annotationValue.getValue();
    }
}
