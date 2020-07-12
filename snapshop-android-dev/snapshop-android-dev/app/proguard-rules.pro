# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in addInfo.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn com.squareup.okhttp.**

-dontwarn okhttp3.**
-dontwarn kotlin.**
-dontwarn javax.annotation.**
-dontwarn com.google.android.gms.**
-dontwarn androidx.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-keep public class * extends android.app.Activity
-printseeds obfuscation/seeds.txt
-printusage obfuscation/unused.txt
-printmapping obfuscation/mapping.txt
-optimizations !method/removal/parameter
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# Model
-keep class com.sg.core.model.** { *; }
-keep class com.sg.core.param.** { *; }
-keep class com.sg.core.vo.** { *; }
-keep class com.sg.snapshop.constant.** { *; }
-keep class com.sg.core.util.** { *; }

# Koin
-keepclassmembers class androidx.lifecycle.** { *; }
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.*
-keepnames class android.arch.lifecycle.ViewModel
