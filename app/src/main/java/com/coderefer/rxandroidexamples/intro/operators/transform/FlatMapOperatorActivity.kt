/*
 * Copyright  © www.coderefer.com All Rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coderefer.rxandroidexamples.intro.operators.transform

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.coderefer.rxandroidexamples.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


private const val TAG = "FlatMapOperator"
class FlatMapOperatorActivity : AppCompatActivity() {

    private val items: List<Int> = listOf(1,2,3,4,5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_map_operator)



        Observable.fromIterable(items)
                .flatMap {
                    Observable.just(it * 2)
                            .delay(it/10L, TimeUnit.SECONDS)
                }
                .doOnNext{
                    Log.d(TAG, it.toString())
                }
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
