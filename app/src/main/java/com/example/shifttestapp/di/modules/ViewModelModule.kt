package com.example.shifttestapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.shifttestapp.di.viewModelFactory.ViewModelKey
import com.example.shifttestapp.presentation.userInfo.UserInfoViewModel
import com.example.shifttestapp.presentation.userList.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface  ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    fun bindUserListViewModel(viewModel: UserListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    fun bindLoginViewModel(viewModel: UserInfoViewModel): ViewModel
}