package com.javacodegeeks.advanced.agent;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.NotFoundException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.SupportedAnnotationTypes;

public class SimpleClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            final ClassLoader loader,
            final String className,
            final Class<?> classBeingRedefined,
            final ProtectionDomain protectionDomain,
            final byte[] classfileBuffer ) throws IllegalClassFormatException {
        boolean a = true,b = true;
        boolean c = a & b;
        if (className.endsWith("sun/net/www/protocol/http/HttpURLConnection")) {
            try {
                final ClassPool classPool = ClassPool.getDefault();
                final CtClass clazz = classPool.get("sun.net.www.protocol.http.HttpURLConnection");

                for (final CtConstructor constructor: clazz.getConstructors()) {
                    constructor.insertAfter("System.out.println(this.getURL());");
                }

                byte[] byteCode = clazz.toBytecode();
                clazz.detach();

                return byteCode;
            } catch (final NotFoundException | CannotCompileException | IOException ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }
}
