package com.carmabs.ema.presentation.ui.user

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carmabs.ema.R
import com.carmabs.ema.android.extension.getFormattedString
import com.carmabs.ema.android.navigation.EmaNavigator
import com.carmabs.ema.core.extension.getFormattedString
import com.carmabs.ema.core.navigator.EmaNavigationState
import com.carmabs.ema.core.state.EmaExtraData
import com.carmabs.ema.presentation.base.BaseFragment
import com.carmabs.ema.presentation.ui.home.EmaHomeToolbarViewModel
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.layout_ema_header.*
import org.kodein.di.generic.instance


/**
 *  *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * The toolbar title is set up by provideFixedToolbarTitle() method
 * Use the EmaTheme because it isn,t overriden by parent activity
 * Use the EmaRecycleAdapter with multiple layout in same RecyclerView
 * Transition animation set in navigation_ema_home
 * Add extra view model to get access to EmaHomeToolbarViewModel (View model can be shared if it
 * is attached to activity scope)
 */
class EmaUserFragment : BaseFragment<EmaUserState, EmaUserViewModel, EmaNavigationState>() {

    override val navigator: EmaNavigator<EmaNavigationState>? = null

    private val toolbarViewModel: EmaHomeToolbarViewModel by instance()

    private lateinit var adapter: EmaUserAdapter

    override fun onInitialized(viewModel: EmaUserViewModel) {
        val toolbarViewModel = addExtraViewModel(toolbarViewModel,this,requireActivity())
        viewModel.toolbarViewModel = toolbarViewModel
        setupRecycler(viewModel)

    }

    private fun setupRecycler(viewModel: EmaUserViewModel) {
        adapter = EmaUserAdapter(viewModel)
        rvUser.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rvUser.adapter = adapter
    }

    override val layoutId: Int = R.layout.fragment_user

    override val viewModelSeed: EmaUserViewModel by instance()

    override fun onNormal(data: EmaUserState) {
        tvUserName.text = data.name
        tvUserSurname.text = data.surname
        setupRecyclerList(data)
    }

    private fun setupRecyclerList(data: EmaUserState) {
        adapter.updateList(data.itemList)
    }

    override fun onAlternative(data: EmaExtraData) {

    }

    override fun onSingle(data: EmaExtraData) {

        when (data.type) {
            EmaUserViewModel.SINGLE_EVENT_USER -> {
                val itemLeft = data.extraData as? EmaUserLeftModel
                itemLeft?.also {
                    Toast.makeText(
                            requireContext(),
                            R.string.user_hello_user.getFormattedString(requireContext(), itemLeft.name),
                            Toast.LENGTH_SHORT)
                            .show()
                }
            }

            EmaUserViewModel.SINGLE_EVENT_GROUP -> {
                val itemRight = data.extraData as? EmaUserRightModel
                itemRight?.also {
                    Toast.makeText(
                            requireContext(),
                            R.string.user_hello_group.getFormattedString(requireContext(), itemRight.number),
                            Toast.LENGTH_SHORT)
                            .show()

                }
            }
        }
    }

    override fun onError(error: Throwable) {
    }
}
