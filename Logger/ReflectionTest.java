import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Scanner;

public class ReflectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception,
			ExceptionInInitializerError {
		// TODO Auto-generated method stub

		System.out.println(Double.class.getName());
		System.out.println(ArrayList.class.getName());
		System.out.println(LoggerWriter.class.getName());

		LoggerWriter lg = new LoggerWriter("hello", 2000);
		String string = toString((Object) 5);
		System.out.println(string);

		/*
		 * Scanner scanner = new Scanner(System.in); String classname =
		 * scanner.next();
		 * 
		 * try { Class cl = Class.forName(classname); PrintConstructor(cl);
		 * PrintField(cl); PrintMethod(cl); } catch (ClassNotFoundException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public static void PrintConstructor(@SuppressWarnings("rawtypes") Class cl) {
		StringBuilder sbBuilder = new StringBuilder();
		Constructor[] constructors = cl.getDeclaredConstructors();
		for (Constructor con : constructors) {
			String nameString = con.getName();
			sbBuilder.append(Modifier.toString(con.getModifiers()));
			sbBuilder.append(" ");
			sbBuilder.append(con.getName());
			sbBuilder.append("(");
			for (int i = 0; i < con.getParameterTypes().length; i++) {
				Class parm = con.getParameterTypes()[i];
				sbBuilder.append(parm.getName());
				if (i != con.getParameterTypes().length - 1) {
					sbBuilder.append(',');
				}
			}
			sbBuilder.append(")");
			if (con.getExceptionTypes().length > 0) {
				sbBuilder.append(" throws ");
				for (int i = 0; i < con.getExceptionTypes().length; i++) {
					Class parm = con.getExceptionTypes()[i];
					sbBuilder.append(parm.getName());
					if (i != con.getParameterTypes().length - 1) {
						sbBuilder.append(',');
					}
				}
			}

			sbBuilder.append("\n");
		}
		System.out.println(String.valueOf(sbBuilder));
	}

	public static void PrintField(Class cl) {
		StringBuilder sbBuilder = new StringBuilder();
		Field[] fields = cl.getDeclaredFields();
		for (Field field : fields) {
			sbBuilder.append(Modifier.toString(field.getModifiers()));
			sbBuilder.append(" ");
			sbBuilder.append(field.getType());
			sbBuilder.append(" ");
			sbBuilder.append(field.getName());
			sbBuilder.append("\n");
		}
		System.out.println(String.valueOf(sbBuilder));
	}

	public static void PrintMethod(@SuppressWarnings("rawtypes") Class cl) {
		StringBuilder sbBuilder = new StringBuilder();
		Method[] methods = cl.getDeclaredMethods();
		for (Method method : methods) {
			String nameString = method.getName();
			sbBuilder.append(Modifier.toString(method.getModifiers()));
			sbBuilder.append(" ");
			sbBuilder.append(method.getReturnType().getName());
			sbBuilder.append(" ");
			sbBuilder.append(method.getName());
			sbBuilder.append("(");
			for (int i = 0; i < method.getParameterTypes().length; i++) {
				Class parm = method.getParameterTypes()[i];
				sbBuilder.append(parm.getName());
				if (i != method.getParameterTypes().length - 1) {
					sbBuilder.append(',');
				}
			}
			sbBuilder.append(")");
			if (method.getExceptionTypes().length > 0) {
				sbBuilder.append(" throws ");
				for (int i = 0; i < method.getExceptionTypes().length; i++) {
					Class parm = method.getExceptionTypes()[i];
					sbBuilder.append(parm.getName());
					if (i != method.getParameterTypes().length - 1) {
						sbBuilder.append(',');
					}
				}
			}
			sbBuilder.append("\n");
		}
		System.out.println(String.valueOf(sbBuilder));
	}

	public static String toString(Object object) {
		if (object == null) {
			return "null";
		}
		Class cl = object.getClass();
		StringBuilder sBuilder = new StringBuilder(cl.getName());
		do {
			sBuilder.append('[');
			Field[] fields = cl.getDeclaredFields();
			for (Field field : fields) {
				AccessibleObject.setAccessible(fields, true);

				if (sBuilder.lastIndexOf("[") != (sBuilder.length() - 1)) {
					sBuilder.append(',');
				}
				sBuilder.append(field.getName());
				sBuilder.append("=");
				Class t = field.getType();

				try {
					if (!Modifier.isStatic(field.getModifiers())) {

						if (field.getType().isPrimitive()) {
							sBuilder.append(field.get(object).toString());
						} else {
							sBuilder.append(toString(field.get(object)));// ตÝน้
						}

					}
					/*else {
						if (field.getType().isPrimitive()) {
							sBuilder.append(field.get(null).toString());
						} else {
							toString(field.get(null));// ตÝน้
						}
					}*/

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sBuilder.append(']');
			cl = cl.getSuperclass();
		} while (cl != null);
		return sBuilder.toString();
	}
}
