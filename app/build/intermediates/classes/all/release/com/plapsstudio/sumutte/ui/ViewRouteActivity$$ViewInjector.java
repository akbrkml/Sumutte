// Generated code from Butter Knife. Do not modify!
package com.plapsstudio.sumutte.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ViewRouteActivity$$ViewInjector<T extends com.plapsstudio.sumutte.ui.ViewRouteActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558553, "method 'onFabClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onFabClick();
        }
      });
  }

  @Override public void reset(T target) {
  }
}
