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
package org.softsmithy.lib.util.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import org.softsmithy.lib.util.PositionableAdapter;
import org.softsmithy.lib.util.PositionableComparator;
import org.softsmithy.lib.util.ServiceProvider;

/**
 *
 * @author puce
 */
@SupportedAnnotationTypes("org.softsmithy.lib.util.ServiceProvider")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ServiceProviderAnnotationProcessor extends AbstractProcessor {

    private final Map<String, List<PositionableAdapter<Name>>> serviceProviders = new HashMap<>();
    private final Map<String, List<Element>> elements = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                ServiceProvider serviceProviderAnnotation = element.getAnnotation(ServiceProvider.class);
                if (serviceProviderAnnotation != null && (element instanceof TypeElement)) {
                    TypeElement typeElement = (TypeElement) element;
                    String serviceClassName;
                    try {
                        // Loading class objects throws a MirroredTypeException in javac
                        Class<?> serviceClass = serviceProviderAnnotation.serviceClass();
                        serviceClassName = serviceClass.getName();
                    } catch (MirroredTypeException ex) {
                        TypeMirror serviceClassTypeMirror = ex.getTypeMirror();
                        serviceClassName = serviceClassTypeMirror.toString();
                    }
                    if (!serviceProviders.containsKey(serviceClassName)) {
                        serviceProviders.put(serviceClassName, new ArrayList<PositionableAdapter<Name>>());
                    }
                    if (!elements.containsKey(serviceClassName)) {
                        elements.put(serviceClassName, new ArrayList<Element>());
                    }
                    PositionableAdapter<Name> serviceProvider = new PositionableAdapter<>(typeElement.getQualifiedName(),
                            serviceProviderAnnotation.position());
                    serviceProviders.get(serviceClassName).add(serviceProvider);
                    elements.get(serviceClassName).add(element);
                }
            }
        }
        if (roundEnv.processingOver()) {
            writeApplicationFile();
        }

        return false;
    }

    private void writeApplicationFile() {
        Filer filer = processingEnv.getFiler();
        Messager messager = processingEnv.getMessager();
        for (Map.Entry<String, List<PositionableAdapter<Name>>> serviceProviderEntry : serviceProviders.entrySet()) {
            List<Element> serviceProviderElements = elements.get(serviceProviderEntry.getKey());
            try {
                FileObject serviceProviderFileObject = filer.createResource(StandardLocation.SOURCE_OUTPUT, "",
                        "META-INF/services/" + serviceProviderEntry.getKey(),
                        serviceProviderElements.toArray(new Element[serviceProviderElements.size()]));
                Collections.sort(serviceProviderEntry.getValue(), new PositionableComparator());
                try (Writer writer = serviceProviderFileObject.openWriter()) {
                    for (PositionableAdapter<Name> serviceProviderDescription : serviceProviderEntry.getValue()) {
                        writer.append(serviceProviderDescription.getAdapted());
                        writer.append("\n");
                    }
                }

            } catch (IOException ex) {
                messager.printMessage(Diagnostic.Kind.ERROR, ex.getMessage());
            }

        }
    }
}
