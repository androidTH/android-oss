package com.kickstarter.libs;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;

import com.kickstarter.BuildConfig;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Locale;

public final class Build {
  private final PackageInfo packageInfo;

  public Build(final @NonNull PackageInfo packageInfo) {
    this.packageInfo = packageInfo;
  }

  public @NonNull String applicationId() {
    return packageInfo.packageName;
  }

  public DateTime dateTime() {
    return new DateTime(BuildConfig.BUILD_DATE, DateTimeZone.UTC).withZone(DateTimeZone.getDefault());
  }

  public boolean isInternal() {
    return BuildConfig.FLAVOR_AUDIENCE.equals("internal");
  }

  public boolean isExternal() {
    return !isInternal();
  }

  /**
   * Returns `true` if the build is compiled in debug mode, `false` otherwise.
   */
  public boolean isDebug() {
    return BuildConfig.DEBUG;
  }

  /**
   * Returns `true` if the build is compiled in release mode, `false` otherwise.
   */
  public boolean isRelease() {
    return !BuildConfig.DEBUG;
  }

  public String sha() {
    return BuildConfig.GIT_SHA;
  }

  public Integer versionCode() {
    return packageInfo.versionCode;
  }

  public String versionName() {
    return packageInfo.versionName;
  }

  public String variant() {
    // e.g. internalDebug, externalRelease
    return new StringBuilder().append(BuildConfig.FLAVOR_AUDIENCE)
      .append(BuildConfig.BUILD_TYPE.substring(0, 1).toUpperCase(Locale.US))
      .append(BuildConfig.BUILD_TYPE.substring(1))
      .toString();
  }
}
