package com.earthwax

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject
import java.util.ArrayList

private const val KEY_SELECTED_ITEMS = "KEY_SELECTED_ITEMS"

class MainFragment : Fragment() {

    private val viewModel by inject<MainViewModel>()

    private var waxAdapter: WaxAdapter? = null

    private var actionMode: ActionMode? = null

    private val actionModeCallback: ActionMode.Callback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.action_delete -> waxAdapter?.let {
                    viewModel.deleteWaxes(it.getSelectedItems())
                }
                R.id.action_delete_all -> viewModel.deleteAllWaxes()
            }
            mode?.finish()
            return true
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            actionMode = mode
            mode?.menuInflater?.inflate(R.menu.main_fragment_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
            waxAdapter?.endSelection()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        waxAdapter?.let { adapter ->
            outState.putParcelableArrayList(KEY_SELECTED_ITEMS, ArrayList(adapter.getSelectedItems()))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        waxAdapter = WaxAdapter(view.context).apply {
            enableActionMode(actionModeCallback) { count ->
                actionMode?.title = getString(R.string.main_fragment_action_mode_title, count)
                if (count == 0) {
                    actionMode?.finish()
                    waxAdapter?.endSelection()
                }
            }
        }
        savedInstanceState?.let { bundle ->
            bundle.getParcelableArrayList<Wax>(KEY_SELECTED_ITEMS)?.let { selectedWaxes ->
                waxAdapter?.setSelection(selectedWaxes)
            }
        }
        with(waxesRecyclerView) {
            adapter = waxAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.waxes.observe(this, Observer { waxes ->
            waxAdapter?.setWaxes(waxes)
        })
        addWaxButton.setOnClickListener {
            viewModel.addRandomWax()
        }
    }

}