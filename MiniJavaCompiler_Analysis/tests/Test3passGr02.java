class testmain {

	public static void main (String[] args) {
		B a;
		A b;
		/* end of Decl */
		a = new B();
		a.test();
		b = new A(); 
		return;
	}
}

class A {
	int a;
	boolean b;
	public void test() {

		return ;
	}
	static int test2(){
		return (7+8);
	}
}
class B extends A {
	int b;
	public void fest(){
		return;
	}
}
