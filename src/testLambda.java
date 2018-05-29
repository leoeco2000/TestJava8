import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.apache.commons.lang3.ArrayUtils;
import java.util.function.*;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class User{
	private String name = null;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

public class testLambda {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ScriptException {
//		Method method = testLambda.class.getMethod( "main", String[].class );
//		for( final Parameter parameter: method.getParameters() ) {
//			System.out.println( "Parameter: " + parameter.getName() );
//		}
		//lambda
//		List<String> arrNew = new ArrayList<String>();
//		String[] arr = {"1","2","3"};
//		Arrays.asList(arr).forEach(
//			(String e)->{
//				if(!e.equals("2")) {
//					arrNew.add(e);
//				}
//			}
//		);
//		arrNew.forEach(
//			(String e)->{
//				System.out.println(e);
//			}
//		);
		//Optional
//		Optional< String > fullName = Optional.ofNullable( null );
//		fullName = Optional.of("Tom");
//		System.out.println(
//			fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" )
//		);
		//构造器
//	    Supplier<User> supplier = ()->new User();
//	    User user = supplier.get();
//	    user.setName("Tom");
//	    System.out.println(user.getName());
		//javascript
//		ScriptEngineManager manager = new ScriptEngineManager();
//		ScriptEngine engine = manager.getEngineByName( "JavaScript" );
//
//		System.out.println( engine.getClass().getName() );
//		System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
		//并行
//		long[] arrayOfLong = new long [ 20000 ];		
//
//        Arrays.parallelSetAll( arrayOfLong,
//            index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
//        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
//            i -> System.out.print( i + " " ) );
//        System.out.println();
//
//        Arrays.parallelSort( arrayOfLong );
//        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
//            i -> System.out.print( i + " " ) );
//        System.out.println();
//		
		//stream
//		Stream<String> stream = Stream.of("I", "love", "you", "too");
//		stream.forEach(str -> System.out.println(str));

		String[] arr = {"I", "love", "you", "too"};
		Stream<String> stream1= Arrays.stream(arr);
		stream1.filter(str -> str.length()==3)
		    .forEach(str -> System.out.println(str));
		System.out.println(1 << 4);
	}
}
