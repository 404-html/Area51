package compiler.IR;


import compiler.PrettyPrinter;

public class MJBoolean extends MJExpression {

	private enum MJBooleanValues {
		True, False
	};

	private MJBooleanValues value;

	public static MJBoolean False = new MJBoolean(MJBooleanValues.False);
	public static MJBoolean True = new MJBoolean(MJBooleanValues.True);

	public MJBoolean(MJBooleanValues value) {
		this.value = value;
	}

	public boolean isTrue() {
		return (this.value == MJBooleanValues.True);
	}

	public boolean isFalse() {
		return (this.value == MJBooleanValues.False);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		if (this.isFalse()) {
			prepri.print("false");
		} else {
			prepri.print("true");
		}
	}
}
