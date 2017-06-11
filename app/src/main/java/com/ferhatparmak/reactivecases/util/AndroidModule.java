package com.ferhatparmak.reactivecases.util;

import android.app.Activity;
import android.app.Fragment;
import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

@Module
public abstract class AndroidModule {
  @Provides
  static Subject<Activity> activitiesSubject() {
    return PublishSubject.create();
  }

  @Provides
  static Subject<Fragment> fragmentsSubject() {
    return PublishSubject.create();
  }
}
