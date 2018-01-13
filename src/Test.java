import java.time.LocalTime;

public class Test {

	public static void main(String[] args) {
		LocalTime test = LocalTime.now();
//		test.minusNanos(test.getNano());
		System.out.println(test.withNano(0));
		System.out.println(test);
	}

}
