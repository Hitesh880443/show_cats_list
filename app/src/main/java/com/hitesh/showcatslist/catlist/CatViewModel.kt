/**
 * Created by Hitesh Sutar 880443 on 6/12/2019.
 */

package com.hitesh.showcatslist.catlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.hitesh.showcatslist.model.Pet
import com.hitesh.showcatslist.utils.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class CatViewModel(private val repo: CatRepo) : ViewModel() {

    var mCatList: MutableLiveData<HashMap<String, ArrayList<Pet>>> = MutableLiveData()
    var mState: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var mSubscription: Disposable

    fun loadList() {
        mSubscription = repo.getDataFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Util.filterList(it)
            }
            .subscribeWith(object : DisposableObserver<HashMap<String, ArrayList<Pet>>>() {
                override fun onComplete() {
                    mState.value = true
                }

                override fun onNext(t: HashMap<String, ArrayList<Pet>>) {
                    mCatList.value = t
                }

                override fun onError(e: Throwable) {
                    mState.value = false
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        if (::mSubscription.isInitialized) {
            mSubscription.dispose()
        }
    }


}