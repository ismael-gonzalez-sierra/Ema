package com.carmabs.ema.presentation.injection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.carmabs.ema.android.ui.dialog.EmaBaseDialogProvider
import com.carmabs.ema.presentation.DIALOG_TAG_LOADING
import com.carmabs.ema.presentation.DIALOG_TAG_SIMPLE
import com.carmabs.ema.presentation.dialog.loading.LoadingDialogProvider
import com.carmabs.ema.presentation.dialog.simple.SimpleDialogProvider
import com.carmabs.ema.presentation.ui.backdata.userlist.EmaBackUserViewModel
import com.carmabs.ema.presentation.ui.backdata.creation.EmaBackUserCreationViewModel
import com.carmabs.ema.presentation.ui.error.EmaErrorViewModel
import com.carmabs.ema.presentation.ui.home.EmaHomeToolbarViewModel
import com.carmabs.ema.presentation.ui.home.EmaHomeViewModel
import com.carmabs.ema.presentation.ui.user.EmaUserViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 *  *<p>
 * Copyright (c) 2020, Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 *
 * Created by: Carlos Mateo Benito on 20/1/19.
 */

fun fragmentInjection(fragment: Fragment) = Kodein.Module(name = "FragmentModule") {

    bind<Fragment>() with singleton { fragment }

    bind<FragmentManager>() with singleton { fragment.requireActivity().supportFragmentManager }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_SIMPLE) with provider { SimpleDialogProvider(instance()) }

    bind<EmaBaseDialogProvider>(tag = DIALOG_TAG_LOADING) with provider { LoadingDialogProvider(instance()) }

    bind<EmaHomeViewModel>() with singleton { EmaHomeViewModel(instance(),instance()) }

    bind<EmaUserViewModel>() with singleton { EmaUserViewModel(instance()) }

    bind<EmaBackUserViewModel>() with singleton { EmaBackUserViewModel() }

    bind<EmaBackUserCreationViewModel>() with singleton { EmaBackUserCreationViewModel(instance()) }
}