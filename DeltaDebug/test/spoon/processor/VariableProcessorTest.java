package spoon.processor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mdkt.compiler.InMemoryJavaCompiler;

import fr.univ_lille1.m2iagl.spoon.processor.VariableProcessor;
import spoon.Launcher;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.NameFilter;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.resources.IFoo;

public class VariableProcessorTest {
	  @Test
	  public void example() throws Exception {
		  Launcher l = new Launcher();
		  
		  // required for having IFoo.class in the classpath in Maven
		  l.addInputResource("test/spoon/resources/Foo1.java");
		  l.buildModel();
		  
		  CtClass foo = (CtClass) l.getFactory().Package().getRootPackage().getElements(new NameFilter("Foo1")).get(0);
		  CtMethod cfoo =  (CtMethod) foo.getElements(new TypeFilter(CtMethod.class)).get(0);
		  
		  Class<?> fooClass = InMemoryJavaCompiler.compile(foo.getQualifiedName(), "package "+foo.getPackage().getQualifiedName()+";"+foo.toString());
		  IFoo x = (IFoo) fooClass.newInstance();
		  
		  
		  assertEquals(0, cfoo.getElements(new Filter<CtInvocation<?>>() {
				@Override
				public boolean matches(CtInvocation<?> arg0) {
					return arg0.getExecutable().getSimpleName().equals("debug");
				}		  
			  }).size());
		  
		  cfoo = VariableProcessor.transform(cfoo);
		  
		  assertEquals(1, cfoo.getElements(new Filter<CtInvocation<?>>() {
			@Override
			public boolean matches(CtInvocation<?> arg0) {
				return arg0.getExecutable().getSimpleName().equals("debug");
			}		  
		  }).size());

	}
}
