public class ConstructorTest {

	/**
	 * @param args
	 */

	static {
		System.out.println("��ð����ҿɲ��ǹ�����Ŷ");
	}

	public static void main(String[] args) {
		ConstructorTest aConstructorTest = new ConstructorTest(10);
		// System.out.println(aConstructorTest.Gettesta());
		// System.out.println(aConstructorTest.Getb());
	}

	private int testa;
	private int b = Geta();
	
	{//��ʼ���飬��b���и�ֵ
		b = 1000;
	}

	private static int Geta() {
		return 10;
	}

	public ConstructorTest() {
		System.out.println("�����޲������캯����b=" + b);
		testa = 3;
		System.out.println("�����޲������캯����testa=" + testa);
	}

	public ConstructorTest(int a) {
		this();
		System.out.println("�в������캯����b=" + b);
		testa = a;
		System.out.println("�в������캯����testa" + testa);
	}

	public String Gettesta() {
		return String.valueOf(testa);
	}

	public String Getb() {
		return String.valueOf(b);
	}

	public String ToString() {
		return String.valueOf(testa);
	}

}
