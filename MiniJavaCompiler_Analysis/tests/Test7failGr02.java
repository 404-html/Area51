class testmain {

	public static void main (String[] args) {
		B b;
		A a;
		/* end of Decl */
		b = new B();
		a.test(); 
		a = new B();
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
