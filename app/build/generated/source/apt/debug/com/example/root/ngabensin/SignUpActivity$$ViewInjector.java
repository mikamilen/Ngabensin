// Generated code from Butter Knife. Do not modify!
package com.example.root.ngabensin;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SignUpActivity$$ViewInjector<T extends com.example.root.ngabensin.SignUpActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131755261, "field '_nameText'");
    target._nameText = finder.castView(view, 2131755261, "field '_nameText'");
    view = finder.findRequiredView(source, 2131755240, "field '_emailText'");
    target._emailText = finder.castView(view, 2131755240, "field '_emailText'");
    view = finder.findRequiredView(source, 2131755241, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131755241, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131755262, "field '_signupButton'");
    target._signupButton = finder.castView(view, 2131755262, "field '_signupButton'");
    view = finder.findRequiredView(source, 2131755263, "field '_loginLink'");
    target._loginLink = finder.castView(view, 2131755263, "field '_loginLink'");
  }

  @Override public void reset(T target) {
    target._nameText = null;
    target._emailText = null;
    target._passwordText = null;
    target._signupButton = null;
    target._loginLink = null;
  }
}
