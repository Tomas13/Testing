
package assignment.kz.di

import android.app.Application
import android.content.Context
import assignment.kz.App
import assignment.kz.data.SupermarketRepository
import assignment.kz.data.network.NetworkService
import assignment.kz.ui.detail.DetailPhotoViewModel
import assignment.kz.ui.main.MainFragment
import assignment.kz.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DiModule::class])
interface DiComponent {

    val supermarketRepository: SupermarketRepository

    val networkService: NetworkService

    fun inject(app: App)

    fun context(): Context

    fun application(): Application

    fun inject(mainViewModel: MainViewModel)
    fun inject(detailPhotoViewModel: DetailPhotoViewModel)
    fun inject(mainFragment: MainFragment)
}