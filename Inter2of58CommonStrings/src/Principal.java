public class Principal {

	public static void main(String[] args) {
		String bucky = "buckyrobersbuckyroberts";
		String a = "hello ";
		String b = "world";
		String c = "SUPER";
		String d = "             space             Waster";
		String e = "i HAVe alwAYS seeN greATness In yOu";
		
		String [][][] x = {{{"helos"},{"mere"},{"ssd"},{"gggg"}},{{"helos"},{"mere"},{"sdddxxxsd"},{"ssd"},{"gggg"}}};
		
		
		System.out.println(bucky.indexOf('k'));
		System.out.println(bucky.indexOf('k',5));
		System.out.println(bucky.indexOf("rob"));
		System.out.println(bucky.indexOf("rob", 7));
		System.out.println(a.concat(b));
		System.out.println(a.replace('h', 'F'));
		System.out.println(a.toUpperCase());
		System.out.println(c.toLowerCase());
		System.out.println(d.trim());
		System.out.println(b.substring(0,1).toUpperCase() + b.substring(1));
		System.out.println(e.substring(0,1).toUpperCase() + e.substring(1).toLowerCase());
	}
}
