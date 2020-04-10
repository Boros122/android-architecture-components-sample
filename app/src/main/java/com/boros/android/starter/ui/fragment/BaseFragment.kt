package com.boros.android.starter.ui.fragment

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.boros.android.starter.R
import com.boros.android.starter.event.InitialEvent
import com.boros.android.starter.model.ToolbarModel
import com.boros.android.starter.ui.activity.MainActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

open class BaseFragment : Fragment() {

    private val TAG = this.javaClass.name

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttachFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        Log.d(TAG, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
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

    }

}