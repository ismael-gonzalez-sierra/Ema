package com.carmabs.ema.presentation.ui.backdata.userlist;

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.R
import com.carmabs.ema.core.state.EmaExtraData
import com.carmabs.ema.presentation.base.BaseFragment
import com.carmabs.ema.presentation.ui.backdata.EmaBackNavigator
import kotlinx.android.synthetic.main.fragment_back.*
import org.kodein.di.generic.instance

/**
 * <p>
 * Copyright (c) 2019, Babel Sistemas de Información. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:carlos.mateo@babel.es”>Carlos Mateo</a>
 * <p>
 * Date: 2019-11-07
 */

class EmaBackUserFragment : BaseFragment<EmaBackUserState, EmaBackUserViewModel, EmaBackNavigator.Navigation>() {

    override val inputStateKey: String? = null

    private val adapter : EmaBackUserAdapter by lazy { EmaBackUserAdapter() }
    override fun onInitialized(viewModel: EmaBackUserViewModel) {
        setupRecycler()
        setupButton(viewModel)
    }

    private fun setupButton(viewModel: EmaBackUserViewModel) {
        bBack.setOnClickListener {
            viewModel.onActionAddUser()
        }
    }

    private fun setupRecycler() {
        rvBack.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        rvBack.adapter = adapter
    }

    override val fragmentViewModelScope: Boolean = true

    override fun getFragmentLayout(): Int  = R.layout.fragment_back

    override val viewModelSeed: EmaBackUserViewModel by instance()

    override val navigator: EmaBackNavigator by instance()

    override fun onNormal(data: EmaBackUserState) {
        adapter.updateList(data.listUsers)
        tvBackNoUsers.visibility = checkVisibility(data.noUserVisibility)
        rvBack.visibility = checkVisibility(!data.noUserVisibility,gone = false)
    }

    override fun onLoading(data: EmaExtraData) {

    }

    override fun onSingle(data: EmaExtraData) {

    }

    override fun onError(error: Throwable) {

    }

}