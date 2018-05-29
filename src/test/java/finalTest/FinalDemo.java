package test.java.finalTest;

public class FinalDemo {
	public static void main(String[] args) {
		final FinalString fs = new FinalString("1");
		fs.setStr("2");
		System.out.println(fs.getStr());

		final FinalString fss = new FinalString("333");
//		fs = fss;
		
		
		final String[] strs0 = {"123","234"};
		final String[] strs1 = {"345","456"};
//		strs1 = strs0;
		strs1[1] = "333";
		System.out.println(strs1[1]);
		
	}
}

class FinalString {
	private String str;

	public FinalString(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}