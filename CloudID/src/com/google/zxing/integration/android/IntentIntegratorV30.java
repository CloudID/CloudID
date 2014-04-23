
package com.google.zxing.integration.android;

import android.app.Fragment;
import android.content.Intent;

public final class IntentIntegratorV30 extends IntentIntegrator {

  private final Fragment fragment;

  /**
   * @param fragment Fragment to handle activity response.
   */
  public IntentIntegratorV30(Fragment fragment) {
    super(fragment.getActivity());
    this.fragment = fragment;
  }

  @Override
  protected void startActivityForResult(Intent intent, int code) {
    fragment.startActivityForResult(intent, code);
  }

}