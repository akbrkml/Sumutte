// Generated code from Butter Knife. Do not modify!
package com.plapsstudio.sumutte.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class AddRouteActivity$$ViewInjector<T extends com.plapsstudio.sumutte.ui.AddRouteActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558554, "field 'etFrom' and method 'etFrom'");
    target.etFrom = finder.castView(view, 2131558554, "field 'etFrom'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.etFrom();
        }
      });
    view = finder.findRequiredView(source, 2131558557, "field 'spDestination'");
    target.spDestination = finder.castView(view, 2131558557, "field 'spDestination'");
    view = finder.findRequiredView(source, 2131558556, "method 'bntAddMap'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.bntAddMap();
        }
      });
    view = finder.findRequiredView(source, 2131558555, "method 'mps'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.mps();
        }
      });
    view = finder.findRequiredView(source, 2131558558, "method 'getRoute'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getRoute();
        }
      });
  }

  @Override public void reset(T target) {
    target.etFrom = null;
    target.spDestination = null;
  }
}
