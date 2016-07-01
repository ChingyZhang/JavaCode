import java.util.Scanner;

public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String input = in.next().toUpperCase();
		Size size = Enum.valueOf(Size.class, input);
		System.out.println(size.ordinal());
		System.out.println(size.compareTo(Size.LARGE));
	}

	enum Size {

		SMALL, MEDIA, LARGE, EXTAL_LARGE;
	}
}
