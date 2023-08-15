package com.tech.wavesfood.firebase.di

import com.tech.wavesfood.firebase.firebaseAuth.repository.AuthRepository
import com.tech.wavesfood.firebase.firebaseAuth.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//dependency provide of interface here

@Module
@InstallIn(ViewModelComponent::class)   //this is define scope in whole project,now here scope till viewModel
abstract class RepositoryModule {

    @Binds
    abstract fun providesFirebaseAuthRepository(
        repo: AuthRepositoryImpl
    ): AuthRepository
}