import com.google.common.collect.ImmutableList;
import io.vertx.core.json.JsonArray;
import junit.framework.TestCase;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func1;
import rx.observables.BlockingObservable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MasteringObservables extends TestCase
{


    @Test
    public void testObservable1()
    {

//        List<String> strings = new ArrayList<String>();
//        strings.add("Frog");
//        strings.add("Cat");
//        strings.add("Dog");
//
//        Observable<List<String>> ob = Observable.from(strings);
//
//
//        List<String> stringsback = ob.flatMapIterable(items -> items)
//                .collect(ImmutableList.Builder<String>::new, new Action2<ImmutableList.Builder<String>,String>() {
//                    @Override
//                    public void call(ImmutableList.Builder<String> o, String o2) {
//                        o.add(o2);
//                    }
//                })
//                .toSingle().toBlocking().value().build();
//
//        stringsback.forEach(s -> System.out.println(s));
//
//        assertEquals(strings,stringsback);

    }

    /**
     * Taken from "Mastering observables"
     *  https://developer.couchbase.com/documentation/server/3.x/developer/java-2.0/observables.html
     */
    @Test
    public void testConsumingObservables()
    {
        Observable.just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                              @Override
                              public void call(Integer integer) {
                                  if(integer.equals(2))
                                  {
                                      throw new RuntimeException("I dont like 2s!");
                                  }
                              }
                          }
                )
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.err.println("Whoops: " + throwable.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }

    /**
     * Taken from "Mastering observables"
     *  https://developer.couchbase.com/documentation/server/3.x/developer/java-2.0/observables.html
     */
    @Test
    public void testConsumingObservables2()
    {


        Observable<String> ob = Observable.just("The", "Dave", "Brubeck", "Quartet", "Time", "Out");

        // .subscribe() returns a subscription
        Subscription sub = ob.take(5) // takes only the first 5 values.
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.err.println("Whoops: " + throwable.getMessage());
                    }

                    @Override
                    public void onNext(String name) {
                        System.out.println("Got: " + name);
                    }
                });

    }


    /**
     * It works perfectly fine, the only problem is though chances are you won't see anything printed out.
     * This is because your main thread exits before the background thread had a chance to run and emit values.
     */
    @Test
    public void testFromAsyncToSync()
    {

        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long counter) {
                        System.out.println("Got: " + counter);
                    }
                });

    }


    /**
     * A common way to deal with such a situation is to add a CountDownLatch , which allows you to synchronize between
     * different threads. One thread counts down the latch, the other one waits until it is counted down:
     */
    @Test
    public void testFromAsyncToSync2() throws Exception
    {

        final CountDownLatch latch = new CountDownLatch(5);
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long counter) {
                        latch.countDown();
                        System.out.println("Got: " + counter);
                    }
                });

        latch.await();

//        Note: One common mistake is to use Thread.sleep() instead of a latch to synchronize the execution between threads.
//        This is a bad idea because it not really synchronizes anything, but just keeps one thread alive for a specific
//        amount of time. If the actual calls take less time you are wasting time, and if it takes longer you won't get the
//        desired effect. If you do this in unit tests, be prepared for a good amount of non-determinism and randomly failing
//        tests. Always use a CountDownLatch.

    }

    /**
     * A technique unique to Observables is to convert it into a BlockingObservable to achieve the same effect.
     * In simple terms, it converts a Observable into a Iterable and making it execute on the caller thread, blocking
     * it until one or more values arrive. This technique is used extensively in the documentation to show concepts,
     * while not having to deal with CountDownLatches all the time. It can also be used if you for some reason are not
     * able to use asynchronous computations.
     *
     * The conversion itself doesn't do any blocking in the first place, only subsequent calls will:
     * @throws Exception
     */
    @Test
    public void testFromAsyncToSyncBlockingObservable() throws Exception
    {
        // This does not block.
        BlockingObservable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10) // without this it would block forever.
                .toBlocking();

        // This blocks and is called for every emitted item.
        observable.forEach(new Action1<Long>() {
            @Override
            public void call(Long counter) {
                System.out.println("Got: " + counter);
            }
        });
    }

    /**
     * If you know that only a single value is every returned, you can use the single() method:
     * @throws Exception
     */
    @Test
    public void testFromAsyncToSyncBlockingObservableSingle() throws Exception
    {
        int value = Observable
                .just(1)
                .toBlocking()
                .single();
        assertTrue(value == 1);
    }

    /**
     * Be aware though that if more items get emitted, you get an exception:
     * (Same happenes if no item gets emitted).
     * @throws Exception
     */
    @Test
    public void testFromAsyncToSyncBlockingObservableSingleWithMultipleItems()
    {
        int value = 0;
        try
        {
            value = Observable.just(1,2).toBlocking().single();
        }catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        assertTrue(value == 0);
    }

    /**
     * As an alternative, you can use singleOrDefault() so that a fallback value gets returned.
     *
     * You can use this technique with the Java SDK if you are loading a document and it does not exist:
     */
    @Test
    public void testFromAsyncToSyncBlockingObservableSingleOrDefault()
    {
        Integer value = 0;
        value = Observable.just(1).toBlocking().singleOrDefault(null);
        if (value == null) {

            System.err.println("Document not found!");
        } else {
            System.out.println(value);
        }
    }

    /**
     * One last thing that comes in handy with blocking calls: sometimes you want to collect all emitted values into a list.
     * You can combine the blocking calls with the toList() operator to achieve something like this:
     */
    @Test
    public void testObservableToList()
    {
        Observable<Integer> ob = Observable.just(1, 2, 3);
        List<Integer> list = ob.toList().toBlocking().single();
        // Prints: [1, 2, 3]
        System.out.println(list);
    }

    @Test
    public void testObservableJsonArray()
    {

        List<String> strings = new ArrayList<String>();
        strings.add("Frog");
        strings.add("Cat");
        strings.add("Dog");
        JsonArray ja = new JsonArray(strings);

        Observable<JsonArray> ob = Observable.just(ja);

        // pushing the json array from the observable
        JsonArray out = ob.toBlocking().single();
        System.out.println(out);
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        out.forEach( i -> builder.add(i.toString()) );

        List<String> outList = builder.build();
        System.out.println(strings);
        System.out.println(outList);
    }

    /**
     * Observables can transform their values in various ways. One of the most basic ones is map(), which converts the
     * incoming value into a different one. You surely like division, so here is the FizzBuzz game:
     */
    @Test
    public void testTransformingObservables()
    {
        // push numbers every 10 milliseconds.
        Observable<Long> ob = Observable.interval(10, TimeUnit.MILLISECONDS).take(20);

        // map the values from the original observable from long to string.
        BlockingObservable<String> ob2 = ob.map(new Func1<Long, String>() {
            @Override
            public String call(Long input) {
                if (input % 3 == 0) {
                    return "Fizz";
                } else if (input % 5 == 0) {
                    return "Buzz";
                }
                return Long.toString(input);
            }
        }).toBlocking();

        ob2.forEach(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

}
