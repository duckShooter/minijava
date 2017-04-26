package compiler.parser.syn;

public class MutableInt {
	private int value;
	
	public MutableInt(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public void increment(){
		value++;
	}
	public int getAndIncrement(){
		int val = value;
		value++;
		return val;
	}
	public void assignValue(int value){
		this.value = value;
	}
	
}
