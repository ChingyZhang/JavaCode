public class ConstructorTest {

	/**
	 * @param args
	 */

	static {
		System.out.println("你好啊，我可不是构造器哦");
	}

	public static void main(String[] args) {
		ConstructorTest aConstructorTest = new ConstructorTest(10);
		// System.out.println(aConstructorTest.Gettesta());
		// System.out.println(aConstructorTest.Getb());
	}

	private int testa;
	private int b = Geta();
	
	{//初始化块，对b进行赋值
		b = 1000;
	}

	private static int Geta() {
		return 10;
	}

	public ConstructorTest() {
		System.out.println("进入无参数构造函数了b=" + b);
		testa = 3;
		System.out.println("进入无参数构造函数了testa=" + testa);
	}

	public ConstructorTest(int a) {
		this();
		System.out.println("有参数构造函数中b=" + b);
		testa = a;
		System.out.println("有参数构造函数中testa" + testa);
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
