package com.android.iBotta.rx;


import java.util.concurrent.Callable;

import io.reactivex.functions.Function;


public interface Func0<R> extends Function, Callable<R> {
    R call();
}
