package com.ferhatparmak.reactivecases.main;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.subjects.Subject;

@Module
public abstract class MainModule {
  @Provides
  static Observable<MainView> provideMainViews(Subject<Activity> activitiesSubject) {
    return activitiesSubject.ofType(MainView.class);
  }
}
