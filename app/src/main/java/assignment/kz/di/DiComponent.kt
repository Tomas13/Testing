/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package assignment.kz.di

import android.app.Activity
import android.app.Application
import android.content.Context
import assignment.kz.App
import assignment.kz.data.SupermarketRepository
import assignment.kz.data.network.NetworkService
import assignment.kz.ui.detail.DetailPhotoViewModel
import assignment.kz.ui.main.MainFragment
import assignment.kz.ui.main.MainViewModel
import com.example.data.PhotoRepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
@Component(modules = arrayOf(DiModule::class,
        PhotoRepositoryModule::class,
        SchedulerFactoryModule::class
))
interface DiComponent {

    val supermarketRepository: SupermarketRepository

    val networkService: NetworkService

    fun inject(app: App)

    fun context(): Context

    fun application(): Application

    fun inject(scanActivity: Activity)


    fun inject(mainViewModel: MainViewModel)
    fun inject(detailPhotoViewModel: DetailPhotoViewModel)
    fun inject(mainFragment: MainFragment)
}