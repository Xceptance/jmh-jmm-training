package org.sample.tbd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.misc.FastRandom;
import org.misc.RandomUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class LambdaForEach
{
    @Param({"1", "10", "20", "100", "1000"})
    int SIZE;

    private FastRandom r = new FastRandom(7L);
    private List<String> data = new ArrayList<>(SIZE);

    @Setup
    public void setup()
    {
        for (int i = 0; i < SIZE; i++)
        {
            data.add(RandomUtils.randomString(r, r.nextInt(10)));
        }
    }

    @Benchmark
    public List<String> classic()
    {
        final List<String> target = new ArrayList<>(SIZE);
        for (int i = 0; i < data.size(); i++)
        {
            target.add(data.get(i));
        }

        return target;
    }

    @Benchmark
    public List<String> classicUnrollable()
    {
        final List<String> target = new ArrayList<>(SIZE);

        int size = data.size();
        for (int i = 0; i < size; i++)
        {
            target.add(data.get(i));
        }

        return target;
    }

    @Benchmark
    public List<String> forEnhanced()
    {
        final List<String> target = new ArrayList<>(SIZE);
        for (final String s : data)
        {
            target.add(s);
        }

        return target;
    }

    @Benchmark
    public List<String> forEach()
    {
        final List<String> target = new ArrayList<>(SIZE);
        data.forEach(target::add);

        return target;
    }

    //    JDK 8 171
    //    Benchmark                        (SIZE)  Mode  Cnt     Score   Error  Units
    //    LambdaForEach.classic                 1  avgt    2    27.220          ns/op
    //    LambdaForEach.classic                10  avgt    2   107.071          ns/op
    //    LambdaForEach.classic                20  avgt    2   199.553          ns/op
    //    LambdaForEach.classic               100  avgt    2   912.354          ns/op
    //    LambdaForEach.classic              1000  avgt    2  6204.544          ns/op
    //    LambdaForEach.classicUnrollable       1  avgt    2    25.931          ns/op
    //    LambdaForEach.classicUnrollable      10  avgt    2    82.813          ns/op
    //    LambdaForEach.classicUnrollable      20  avgt    2   144.411          ns/op
    //    LambdaForEach.classicUnrollable     100  avgt    2   783.250          ns/op
    //    LambdaForEach.classicUnrollable    1000  avgt    2  7104.281          ns/op
    //    LambdaForEach.forEach                 1  avgt    2    31.783          ns/op
    //    LambdaForEach.forEach                10  avgt    2    79.296          ns/op
    //    LambdaForEach.forEach                20  avgt    2   122.797          ns/op
    //    LambdaForEach.forEach               100  avgt    2   494.040          ns/op
    //    LambdaForEach.forEach              1000  avgt    2  4636.646          ns/op
    //    LambdaForEach.forEnhanced             1  avgt    2    26.813          ns/op
    //    LambdaForEach.forEnhanced            10  avgt    2    82.576          ns/op
    //    LambdaForEach.forEnhanced            20  avgt    2   141.305          ns/op
    //    LambdaForEach.forEnhanced           100  avgt    2   598.028          ns/op
    //    LambdaForEach.forEnhanced          1000  avgt    2  7653.733          ns/op
    
    // JDK 10
    //    Benchmark                        (SIZE)  Mode  Cnt     Score   Error  Units
    //    LambdaForEach.classic                 1  avgt    2    18.117          ns/op
    //    LambdaForEach.classic                10  avgt    2    78.684          ns/op
    //    LambdaForEach.classic                20  avgt    2   151.849          ns/op
    //    LambdaForEach.classic               100  avgt    2   711.698          ns/op
    //    LambdaForEach.classic              1000  avgt    2  4137.045          ns/op
    //    LambdaForEach.classicUnrollable       1  avgt    2    21.031          ns/op
    //    LambdaForEach.classicUnrollable      10  avgt    2    54.524          ns/op
    //    LambdaForEach.classicUnrollable      20  avgt    2   110.808          ns/op
    //    LambdaForEach.classicUnrollable     100  avgt    2   391.332          ns/op
    //    LambdaForEach.classicUnrollable    1000  avgt    2  4160.066          ns/op
    //    LambdaForEach.forEach                 1  avgt    2    20.653          ns/op
    //    LambdaForEach.forEach                10  avgt    2   120.495          ns/op
    //    LambdaForEach.forEach                20  avgt    2   220.135          ns/op
    //    LambdaForEach.forEach               100  avgt    2   919.583          ns/op
    //    LambdaForEach.forEach              1000  avgt    2  9343.400          ns/op
    //    LambdaForEach.forEnhanced             1  avgt    2    18.981          ns/op
    //    LambdaForEach.forEnhanced            10  avgt    2   106.027          ns/op
    //    LambdaForEach.forEnhanced            20  avgt    2   183.889          ns/op
    //    LambdaForEach.forEnhanced           100  avgt    2  1094.944          ns/op
    //    LambdaForEach.forEnhanced          1000  avgt    2  9478.919          ns/op

    // JDK 11-EA
    //    Benchmark                        (SIZE)  Mode  Cnt     Score   Error  Units
    //    LambdaForEach.classic                 1  avgt    2    16.731          ns/op
    //    LambdaForEach.classic                10  avgt    2    91.278          ns/op
    //    LambdaForEach.classic                20  avgt    2   158.677          ns/op
    //    LambdaForEach.classic               100  avgt    2   709.837          ns/op
    //    LambdaForEach.classic              1000  avgt    2  3953.942          ns/op
    //    LambdaForEach.classicUnrollable       1  avgt    2    15.908          ns/op
    //    LambdaForEach.classicUnrollable      10  avgt    2    52.680          ns/op
    //    LambdaForEach.classicUnrollable      20  avgt    2    98.640          ns/op
    //    LambdaForEach.classicUnrollable     100  avgt    2   450.648          ns/op
    //    LambdaForEach.classicUnrollable    1000  avgt    2  3421.119          ns/op
    //    LambdaForEach.forEach                 1  avgt    2    21.331          ns/op
    //    LambdaForEach.forEach                10  avgt    2   125.331          ns/op
    //    LambdaForEach.forEach                20  avgt    2   213.802          ns/op
    //    LambdaForEach.forEach               100  avgt    2  1018.362          ns/op
    //    LambdaForEach.forEach              1000  avgt    2  9711.427          ns/op
    //    LambdaForEach.forEnhanced             1  avgt    2    18.558          ns/op
    //    LambdaForEach.forEnhanced            10  avgt    2   100.271          ns/op
    //    LambdaForEach.forEnhanced            20  avgt    2   174.026          ns/op
    //    LambdaForEach.forEnhanced           100  avgt    2   929.878          ns/op
    //    LambdaForEach.forEnhanced          1000  avgt    2  8792.056          ns/op

    // Graal 1.0 RC5
    //    Benchmark                        (SIZE)  Mode  Cnt     Score   Error  Units
    //    LambdaForEach.classic                 1  avgt    2    14.641          ns/op
    //    LambdaForEach.classic                10  avgt    2    59.413          ns/op
    //    LambdaForEach.classic                20  avgt    2   115.281          ns/op
    //    LambdaForEach.classic               100  avgt    2   529.137          ns/op
    //    LambdaForEach.classic              1000  avgt    2  5185.318          ns/op
    //    LambdaForEach.classicUnrollable       1  avgt    2    14.527          ns/op
    //    LambdaForEach.classicUnrollable      10  avgt    2    60.417          ns/op
    //    LambdaForEach.classicUnrollable      20  avgt    2   116.972          ns/op
    //    LambdaForEach.classicUnrollable     100  avgt    2   576.215          ns/op
    //    LambdaForEach.classicUnrollable    1000  avgt    2  5289.327          ns/op
    //    LambdaForEach.forEach                 1  avgt    2    23.286          ns/op
    //    LambdaForEach.forEach                10  avgt    2    96.611          ns/op
    //    LambdaForEach.forEach                20  avgt    2   155.790          ns/op
    //    LambdaForEach.forEach               100  avgt    2   724.394          ns/op
    //    LambdaForEach.forEach              1000  avgt    2  6818.886          ns/op
    //    LambdaForEach.forEnhanced             1  avgt    2    16.830          ns/op
    //    LambdaForEach.forEnhanced            10  avgt    2    72.165          ns/op
    //    LambdaForEach.forEnhanced            20  avgt    2   148.087          ns/op
    //    LambdaForEach.forEnhanced           100  avgt    2   615.688          ns/op
    //    LambdaForEach.forEnhanced          1000  avgt    2  6701.336          ns/op

}
