// Generated code from Butter Knife. Do not modify!
package com.example.root.ngabensin;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ChooseFuel$$ViewInjector<T extends com.example.root.ngabensin.ChooseFuel> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755216, "field 'mSeekbar'");
    target.mSeekbar = finder.castView(view, 2131755216, "field 'mSeekbar'");
    view = finder.findRequiredView(source, 2131755214, "field 'mFuelGaugeView'");
    target.mFuelGaugeView = finder.castView(view, 2131755214, "field 'mFuelGaugeView'");
  }

  @Override public void reset(T target) {
    target.mSeekbar = null;
    target.mFuelGaugeView = null;
  }
}
