// Generated code from Butter Knife. Do not modify!
package com.plapsstudio.sumutte.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class History$$ViewInjector<T extends com.plapsstudio.sumutte.ui.History> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558565, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131558565, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131558564, "field 'emptyText'");
    target.emptyText = finder.castView(view, 2131558564, "field 'emptyText'");
  }

  @Override public void reset(T target) {
    target.recyclerView = null;
    target.emptyText = null;
  }
}
