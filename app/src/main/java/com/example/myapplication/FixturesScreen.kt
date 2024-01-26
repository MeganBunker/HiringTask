package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


internal class FixturesScreen : Fragment(R.layout.fixtures_fragment) {

    private var scrollingContainer: NestedScrollView? = null
    private var fixtureCardLayout: RecyclerView? = null
    private var errorLayout: ConstraintLayout? = null
    private lateinit var fixtureAdapter: FixturesAdapter

    private val fixtureViewModel: FixturesViewModel by viewModels {
        FixturesViewModelFactory(repository = MatchRepository(provideFixtureDAO(requireActivity().application)))
    }

    private fun provideFixtureDAO(application: Application): FixtureDAO {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build().fixtureDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeScreen(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    @SuppressLint("CheckResult")
    private fun initializeScreen(view: View) {
        val context = view.context

        scrollingContainer = view.findViewById(R.id.fixtureScreen_scrollView)
        fixtureCardLayout = view.findViewById(R.id.fixtureScreen_recyclerView)

        errorLayout = view.findViewById(R.id.fixtureScreen_errorLayout)

        fixtureAdapter = FixturesAdapter(listOf())
        fixtureCardLayout?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fixtureAdapter
        }

        createObservable()
            .subscribeOn(Schedulers.io()) // Perform work on the IO thread
            .map { fixtureViewModel.getFixtureInformation() } // Example: Perform network request for each item
            .observeOn(AndroidSchedulers.mainThread()) // Observe on the main thread for UI updates
            .subscribe(
                { handleSuccess(view) }, // onNext
                { error -> handleError(view)
                println(error)
                },   // onError
                { println("Completed")
                handleSuccess(view) }                 // onComplete
            )


    }

    fun handleSuccess(view: View){
        scrollingContainer?.apply { visibility = View.VISIBLE }
        fixtureCardLayout?.apply { visibility = View.VISIBLE }

        errorLayout?.apply { visibility = View.GONE }

        fixtureCardLayout?.apply {
            fixtureAdapter = FixturesAdapter(fixtureViewModel.allMatches.value!!)
            fixtureCardLayout?.adapter = fixtureAdapter
        }
    }

    fun handleError(view: View){
        scrollingContainer?.apply { visibility = View.GONE }
        fixtureCardLayout?.apply { visibility = View.GONE }

        errorLayout?.apply { visibility = View.VISIBLE }
    }

    fun createObservable(): Observable<String> {
        return Observable.create { emitter ->
            emitter.onNext("Data 1")
            emitter.onNext("Data 2")
            emitter.onNext("Data 3")

            emitter.onComplete()
        }
    }

}