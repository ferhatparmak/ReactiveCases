package com.ferhatparmak.reactivecases;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import io.reactivex.Completable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public abstract class AbstractFragment extends Fragment {
  private BehaviorSubject<Boolean> onResumePauseSubject = BehaviorSubject.create();
  private PublishSubject<Object> onDestroySubject = PublishSubject.create();

  public ApplicationComponent getComponent(Context context) {
    Activity activity = (Activity) context;
    App app = (App) activity.getApplication();
    return app.getComponent();
  }

  @Override
  public void onResume() {
    super.onResume();
    onResumePauseSubject.onNext(true);
  }

  @Override
  public void onPause() {
    onResumePauseSubject.onNext(false);
    super.onPause();
  }

  public Completable onResumeCompletable() {
    return onResumePauseSubject
        .takeWhile(onResume -> !onResume)
        .ignoreElements();
  }

  public Completable onPauseCompletable() {
    return onResumePauseSubject
        .takeWhile(onResume -> onResume)
        .ignoreElements();
  }

  public Completable onDestroyCompletable() {
    return onDestroySubject.ignoreElements();
  }

  @Override
  public void onDestroy() {
    onResumePauseSubject.onComplete();
    onDestroySubject.onComplete();
    super.onDestroy();
  }
}
