-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient

-keep class retrofit.** {
    *;
}

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keepattributes *Annotation*

-adaptresourcefilenames **.xsd,**.wsdl,**.xml,**.properties,**.gif,**.jpg,**.png