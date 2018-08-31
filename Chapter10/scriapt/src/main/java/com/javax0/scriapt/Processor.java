package com.javax0.scriapt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.javax0.aptools.FromThe;

@SupportedAnnotationTypes("com.javax0.scriapt.CompileScript")
@SupportedSourceVersion(SourceVersion.RELEASE_9)
public class Processor extends AbstractProcessor {

	@Override
	public boolean process(final Set<? extends TypeElement> annotations,
			final RoundEnvironment roundEnv) {
		for (final Element rootElement : roundEnv.getRootElements()) {
			try {
				processClass(rootElement);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}

	private void processClass(final Element element) throws ScriptException,
			FileNotFoundException {
		for (final AnnotationMirror annotationMirror : element
				.getAnnotationMirrors()) {
			processAnnotation(annotationMirror);
		}
	}

	private void processAnnotation(final AnnotationMirror annotationMirror)
			throws ScriptException, FileNotFoundException {
		final String script = FromThe.annotation(annotationMirror).getStringValue();
		final String engine = FromThe.annotation(annotationMirror).getStringValue("engine");
		execute(script, engine);
	}

	private void execute(final String scriptFileName, final String engineName)
			throws ScriptException, FileNotFoundException {
		final ScriptEngineManager factory = new ScriptEngineManager();
		final ScriptEngine engine;
		if (engineName != null && engineName.length() > 0) {
			engine = factory.getEngineByName(engineName);
		} else {
			engine = factory
					.getEngineByExtension(getExtensionFrom(scriptFileName));
		}
		Reader scriptFileReade = new FileReader(new File(scriptFileName));
		engine.eval(scriptFileReade);
	}

	private String getExtensionFrom(final String scriptFileName) {
		final int indexOfExtension = scriptFileName.lastIndexOf('.');
		if (indexOfExtension == -1) {
			return "";
		} else {
			return scriptFileName.substring(indexOfExtension + 1);
		}
	}
}
