// Generated code from Butter Knife. Do not modify!
package com.plapsstudio.sumutte.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ListRouteAdapter$ViewHolderRoute$$ViewInjector<T extends com.plapsstudio.sumutte.adapter.ListRouteAdapter.ViewHolderRoute> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558582, "field 'txtTujuan'");
    target.txtTujuan = finder.castView(view, 2131558582, "field 'txtTujuan'");
    view = finder.findRequiredView(source, 2131558581, "field 'txtDari'");
    target.txtDari = finder.castView(view, 2131558581, "field 'txtDari'");
    view = finder.findRequiredView(source, 2131558583, "field 'txtDistance'");
    target.txtDistance = finder.castView(view, 2131558583, "field 'txtDistance'");
    view = finder.findRequiredView(source, 2131558584, "field 'txtEta'");
    target.txtEta = finder.castView(view, 2131558584, "field 'txtEta'");
  }

  @Override public void reset(T target) {
    target.txtTujuan = null;
    target.txtDari = null;
    target.txtDistance = null;
    target.txtEta = null;
  }
}
