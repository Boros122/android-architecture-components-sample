package com.boros.android.sample.shared.ui.fragment

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.boros.android.sample.R
import com.boros.android.sample.di.components.MainComponent
import com.boros.android.sample.features.main.MainActivity
import com.boros.android.sample.shared.event.InitialEvent
import com.boros.android.sample.shared.model.ToolbarModel
import com.boros.android.sample.shared.ui.activity.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    private val className = this.javaClass.name

    val mainComponent: MainComponent
        get() = (activity as BaseActivity).mainComponent

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(className, "onAttachFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(className, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(className, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(className, "onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(className, "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        Log.d(className, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(className, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(className, "onPause")
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        Log.d(className, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(className, "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(className, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(className, "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(className, "onDestroy")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        updateMenuIconColor(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateMenuIconColor(menu: Menu?) {
        val size = menu?.size() ?: 0
        context?.let {
            for (i in 0 until size) {
                menu?.get(i)?.icon?.setColorFilter(ContextCompat.getColor(it, R.color.colorToolbarIconTint), PorterDuff.Mode.SRC_ATOP)
            }
        }
    }

    fun updateToolbar(toolbarModel: ToolbarModel) {
        if (activity is MainActivity) {
            (activity as MainActivity).updateToolbar(toolbarModel)
        }
    }

    @Subscribe
    fun initialSubscribe(event: InitialEvent) {
        // Nothing to do here
    }

}