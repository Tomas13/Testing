package assignment.kz.di

import io.reactivex.Scheduler

interface SchedulerFactory {
  val mainScheduler: Scheduler
  val ioScheduler: Scheduler
}
