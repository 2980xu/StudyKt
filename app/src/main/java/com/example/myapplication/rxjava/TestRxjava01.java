package com.example.myapplication.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TestRxjava01 {
    public static void main(String[] args) {
        System.out.println("=================funObservable===================");
        funObservable();

        System.out.println("==================funConsumer==================");
        funConsumer();

        System.out.println("==================funJust==================");
        funJust();

        System.out.println("==================funFromArray==================");
        funFromArray();

        System.out.println("==================funFromIterable==================");
        funFromIterable();

        System.out.println("==================funFromFuture==================");
        funFromFuture();

        System.out.println("==================funFromCallable==================");
        funFromCallable();

        System.out.println("==================funMap==================");
        funMap();

        System.out.println("==================funFlatMap==================");
        funFlatMap();

        System.out.println("==================funConcatMap==================");
        funConcatMap();

        System.out.println("==================funBuffer==================");
        funBuffer();

        System.out.println("==================funConcat==================");
        funConcat();

        System.out.println("==================funConcatArray==================");
        funConcatArray();

        System.out.println("==================funMerge==================");
        funMerge();

        System.out.println("==================funMapOrFlatMap==================");
        funMapOrFlatMap();
    }


    static Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe: ");
        }

        @Override
        public void onNext(Object o) {
            System.out.println("onNext: " + o);

        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError: " + e.toString());

        }

        @Override
        public void onComplete() {
            System.out.println("onComplete: ");

        }
    };

    private static void funObservable() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    emitter.onNext(i);
                }
                emitter.onError(new Throwable("抛出异常🤬🤬🤬"));
                emitter.onComplete();
                //onError和onComplete是互斥关系，
                // 如果先回调onError后回调onComplete，则onComplete不会接收到回调
                // 如果先回调onComplete后回调onError，则onError不会接收到回调并抛出异常
            }
        });

        observable.subscribe(observer);
    }

    //消费者
    private static void funConsumer() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    emitter.onNext(i);
                }
//                emitter.onComplete();
                emitter.onError(new Throwable("抛出异常🤬🤬🤬"));
                //onError和onComplete是互斥关系，
                // 如果先回调onError后回调onComplete，则onComplete不会接收到回调
                // 如果先回调onComplete后回调onError，则onError不会接收到回调并抛出异常
            }
        });

        Disposable d = observable.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("Object accept: " + o);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Throwable accept: " + throwable);
            }
        });
    }

    private static void funJust() {
        Observable observable = Observable.just("1", 2, 'c', 456.789);
        observable.subscribe(observer);
    }

    private static void funFromArray() {
        Observable observable = Observable.fromArray("1", 2, 'c', 456.789, false);
        observable.subscribe(observer);
    }

    private static void funFromIterable() {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
//        list.addAll(list2);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        Observable observable = Observable.fromIterable(list);
        observable.subscribe(observer);
    }

    private static void funFromFuture() {
        Observable observable = Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws ExecutionException, InterruptedException {
                return "null";
            }

            @Override
            public Object get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return null;
            }
        });
        observable.subscribe(observer);
    }

    private static void funFromCallable() {
        Observable observable = Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "bbbbbb";
            }
        });
        observable.subscribe(observer);
    }

    //map：对just事件处理然后重新发送数据
    private static void funMap() {
        Observable observable = Observable.just("123", 456).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object s) throws Exception {
                return s;
            }
        });
        observable.subscribe(observer);
    }

    //flatMap：返回一个新的被观察者，新的被观察者数据是无序的
    private static void funFlatMap() {
        Observable observable = Observable.just("111", "222", "333", "444", "555").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.just(s + " 🤯🤯🤯");
            }
        });
        observable.subscribe(observer);
    }

    //concatMap：返回一个新的被观察者，新的被观察者数据是有序的
    private static void funConcatMap() {
        Observable observable = Observable.just("111", "222", "333", "444", "555").concatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return Observable.just(s + " 😄😄😄");
            }
        });
        observable.subscribe(observer);
    }

    private static void funBuffer() {
        Observable observable = Observable.just("111", "222", "333", "444", "555", "666", "777", "888", "999", "000").buffer(3);
        observable.subscribe(observer);
    }

    //最多可以发送四个被观察者,串行的
    private static void funConcat() {
        Observable observable = Observable.concat(
                Observable.just("1111", "33333"),
                Observable.just("2222"),
                Observable.just("5555"),
                Observable.just("6666"));
        observable.subscribe(observer);
    }

    //可以发送多个被观察者
    private static void funConcatArray() {
        Observable observable = Observable.concatArray(
                Observable.just("1111", "33333"),
                Observable.just("2222"),
                Observable.just("3333"),
                Observable.just("6666"),
                Observable.just("7777"));
        observable.subscribe(observer);
    }

    //最多可以发送四个被观察者,并行的
    private static void funMerge() {
        Observable observable = Observable.merge(
                Observable.just("1111", "33333"),
                Observable.just("2222"),
                Observable.just("5555"),
                Observable.just("6666"));
        observable.subscribe(observer);
    }

    private static void funMapOrFlatMap() {
        InfoRx infoRx1 = new InfoRx();
        infoRx1.info = "info1";
        infoRx1.num = 1;

        InfoRx infoRx2 = new InfoRx();
        infoRx2.info = "info2";
        infoRx2.num = 2;

        InfoRx infoRx3 = new InfoRx();
        infoRx3.info = "info3";
        infoRx3.num = 3;

        List<InfoRx> infoRxList = new ArrayList<>();
        infoRxList.add(infoRx1);
        infoRxList.add(infoRx2);
        infoRxList.add(infoRx3);

        StudentRx studentRx1 = new StudentRx();
        studentRx1.name = "xu1";
        studentRx1.age = 111;
        studentRx1.setInfoRxList(infoRxList);

        StudentRx studentRx2 = new StudentRx();
        studentRx2.name = "xu2";
        studentRx2.age = 222;
        studentRx2.setInfoRxList(infoRxList);

        StudentRx studentRx3 = new StudentRx();
        studentRx3.name = "xu3";
        studentRx3.age = 333;
        studentRx3.setInfoRxList(infoRxList);

        List<StudentRx> studentRxList = new ArrayList<>();
        studentRxList.add(studentRx1);
        studentRxList.add(studentRx2);
        studentRxList.add(studentRx3);

        Observable.fromIterable(studentRxList).map(new Function<StudentRx, List<InfoRx>>() {
                    @Override
                    public List<InfoRx> apply(StudentRx studentRx) throws Exception {
                        return studentRx.getInfoRxList();
                    }
                })
                .subscribe(new Consumer<List<InfoRx>>() {
                    @Override
                    public void accept(List<InfoRx> infoRxList) throws Exception {
                        // 处理或输出infoRxList的逻辑
                        for (InfoRx infoRx : infoRxList) {
                            System.out.println(infoRx.info + ", " + infoRx.num);
                        }
                    }
                });

        System.out.println("===========================================================");

        Observable.fromIterable(studentRxList).flatMap(new Function<StudentRx, ObservableSource<InfoRx>>() {
            @Override
            public ObservableSource<InfoRx> apply(StudentRx studentRx) throws Exception {
                return Observable.fromIterable(studentRx.getInfoRxList());
            }
        }).subscribe(new Consumer<InfoRx>() {
            @Override
            public void accept(InfoRx o) throws Exception {
                System.out.println(o.info + ", " + o.num);
            }
        });
    }
}
