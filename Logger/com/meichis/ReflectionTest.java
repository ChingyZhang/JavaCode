/**
 * 
 */
package com.meichis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @author ChingyZhang
 * 
 */
class Reflection {
	private Reflection() {//˽�й��캯�������������
		System.out.println("com.meichis����Reflection��private  ���췽��");
	}

	protected Reflection(String s, int i) {
		System.out.println("com.meichis����Reflection��protected  ���췽��,s=" + s
				+ "i=" + i);
	}

	public Reflection(String... s) // throws
									// NumberFormatException,NullPointerException
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			sb.append(s + " ");
		}
		System.out.println("com.meichis����Reflection��public ���췽��,s="
				+ sb.toString());
	}
}

public class ReflectionTest {
	static String newline = "\n\r";

	public static void TestConstructor() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Class<?> cla = Reflection.class;
		Constructor<?>[] cons = cla.getDeclaredConstructors();
		StringBuilder sbBuilder = new StringBuilder();

		for (int i = 0; i < cons.length; i++) {
			Constructor<?> con = cons[i];
			int modifier = con.getModifiers();

			sbBuilder.append("��" + (i + 1) + "�����������Ƿ���пɱ������Ĳ���:"
					+ con.isVarArgs() + ". ");
			sbBuilder.append(GetFunModifier(modifier) + "(" + (modifier) + ")"
					+ " ");
			sbBuilder.append(con.getName());
			sbBuilder.append("(");
			for (int j = 0; j < con.getParameterTypes().length; j++) {
				Class<?> par = con.getParameterTypes()[j];
				sbBuilder.append(par.getName());
				if (j != con.getParameterTypes().length - 1) {
					sbBuilder.append(", ");
				}
			}
			sbBuilder.append(")");
			Class<?>[] exception = con.getExceptionTypes();
			if (exception.length > 0) {
				sbBuilder.append(" throws ");

				for (int j = 0; j < exception.length; j++) {
					Class<?> exc = exception[j];
					sbBuilder.append(exc.getName());
					if (j != exception.length - 1) {
						sbBuilder.append(",");
					}
				}
			}
			sbBuilder.append(newline);
			Reflection ref;
			if (Modifier.isPrivate(modifier)) {
				System.out.println("����private");
				// ref = (Reflection) con.newInstance();s
			} else if (Modifier.isProtected(modifier)) {
				ref = (Reflection) con.newInstance("test", 2);
			} else if (Modifier.isPublic(modifier)) {
				System.out.println(" Modifier.PUBLIC=" + Modifier.PUBLIC
						+ " modifier=" + modifier
						+ " Modifier.isPublic(Modifier.PUBLIC):"
						+ Modifier.isPublic(Modifier.PUBLIC)
						+ " Modifier.isPublic(modifier):"
						+ Modifier.isPublic(modifier)
						+ " (Modifier.PUBLIC == modifier):"+(Modifier.PUBLIC == modifier));
				if (Modifier.isPublic(modifier)) {
					System.out.println("Modifier.isPublic(modifier)");
				}
				Object[] objects = new Object[] { new String[] { "str1",
						"str2", "str3" } };
				ref = (Reflection) con.newInstance(objects);
			}
		}
		System.out.println(sbBuilder.toString());
	}

	static String GetFunModifier(int modifier) {
		/*
		 * if (Modifier.isStatic(modifier)) { return "static"; } else
		 */
		if (Modifier.isPublic(modifier)) {
			return "public";
		} else if (Modifier.isProtected(modifier)) {
			return "protected";
		} else if (Modifier.isPrivate(modifier)) {
			return "private";
		} else if (Modifier.isStatic(modifier)) {
			return "static";
		}
		return "";
	}
}