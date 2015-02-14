class StaticField {
static int i = 7;
public static void main(String[] args) {
StaticField obj = new StaticField();
i++;
StaticField.i++;
obj.i++;
System.out.println(StaticField.i + " "+ obj.i);

new StaticField().strMethod();

}

String strMethod(){
	return null;
}

}