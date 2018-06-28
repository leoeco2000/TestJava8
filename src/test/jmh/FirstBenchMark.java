package test.jmh;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BenchmarkMode(Mode.AverageTime) // ���Է���ƽ��ִ��ʱ��
@OutputTimeUnit(TimeUnit.MICROSECONDS) // ��������ʱ������Ϊ΢��
@State(Scope.Thread) // ÿ�������߳�һ��ʵ��
public class FirstBenchMark {
  private static Logger log = LoggerFactory.getLogger(FirstBenchMark.class);

  @Benchmark
  public String stringConcat() {
    String a = "a";
    String b = "b";
    String c = "c";
    String s = a + b + c;
    log.debug(s);
    return s;
  }

  public static void main(String[] args) throws RunnerException {
    // ʹ��һ����������ִ�в��ԣ�ִ��5��warmup��Ȼ��ִ��5�����
    Options opt = new OptionsBuilder()
        .include(FirstBenchMark.class.getSimpleName()).forks(1)
        .warmupIterations(5).measurementIterations(5).build();
    new Runner(opt).run();
  }
}
