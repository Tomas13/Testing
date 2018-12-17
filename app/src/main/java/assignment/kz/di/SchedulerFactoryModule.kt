package assignment.kz.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerFactoryModule {
  @Provides
  @Singleton
  fun schedulerFactory(): SchedulerFactory = SchedulerFactoryImpl()
}
