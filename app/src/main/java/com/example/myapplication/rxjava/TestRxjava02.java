package com.example.myapplication.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class TestRxjava02 {

    public static void main(String[] args) {
//        System.out.println("==================funSubscribeOn==================");
//        funSubscribeOn();

        System.out.println("==================funFilter==================");
        funFilter();

    }

    static Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe: " + " currentThread: " + Thread.currentThread());
        }

        @Override
        public void onNext(Object o) {
            System.out.println("onNext: " + o + " currentThread: " + Thread.currentThread());

        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError: " + e.toString() + " currentThread: " + Thread.currentThread());

        }

        @Override
        public void onComplete() {
            System.out.println("onComplete currentThread: " + Thread.currentThread());

        }
    };

    public static void funSubscribeOn() {
        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("subscribe currentThread: " + Thread.currentThread());
//                Thread.sleep(2000);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onComplete();
            }
        });
        //切换子线程 只会执行上游Schedulers.newThread()代码，后面写的subscribeOn(Schedulers.io())不会执行
//        observable.subscribeOn(Schedulers.newThread()).map(new Function() {
//            @Override
//            public Object apply(Object o) throws Exception {
//                return o;
//            }
//        }).subscribeOn(Schedulers.io()).
//                subscribe(observer);

        observable
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("doOnNext apply currentThread: " + Thread.currentThread());
                    }
                })
                .observeOn(Schedulers.io())//将上游处理数据的线程切换到子线程
                .observeOn(AndroidSchedulers.mainThread())//将下游处理数据的线程切换到主线程
                .map(new Function() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        System.out.println("map apply currentThread: " + Thread.currentThread());
                        return "cccc";
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(observer);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }

    public static void funFilter() {
        Observable observable = Observable.range(1, 10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer < 5; //返回false进行过滤
            }
        });
        observable.subscribe(observer);
    }
}
