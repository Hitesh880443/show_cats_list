/**
 * Created by Hitesh Sutar 880443 on 6/12/2019.
 */

package com.hitesh.showcatslist.catlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.hitesh.showcatslist.R
import com.hitesh.showcatslist.di.component.DaggerMyAppComponent
import com.hitesh.showcatslist.di.module.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: CatViewModel

    @Inject
    lateinit var repo: CatRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        mViewModel = ViewModelProviders.of(this, CatViewModelProvider(repo)).get(CatViewModel::class.java)
        mViewModel.loadList()
        mViewModel
            .mCatList
            .observe(this, Observer {
                it?.let { data ->
                    expandableListView.setAdapter(CatListAdapter(this, ArrayList(data.keys), it))
                }
            })

        mViewModel
            .mState
            .observe(this, Observer {
                pb_progress.visibility = View.GONE
                when (it) {
                    false -> showSnackBar()
                }
            })
    }

    private fun showSnackBar() {
        val snack = Snackbar.make(expandableListView, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry, View.OnClickListener {
                mViewModel.loadList()
            })
        snack.show()
    }

    private fun inject() {
        val injector = DaggerMyAppComponent
            .builder()
            .networkModule(NetworkModule(getString(R.string.BASE_URL)))
            .build()

        injector.inject(this)
    }
}
