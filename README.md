# ANTLR4 on Android

This project is a small demo of how ANTLR4 can be built without Swing dependencies
so that the ANTLR4 runtime library can be used in an Android app. The Android compatible
library is localed in `app/libs` and can be built as follows:

### 1. First clone ANTLR4:

    git clone https://github.com/antlr/antlr4.git

### 2. Checkout tag `4.3`:

    git checkout 4.3

### 3. Remove the entire package:

    rm -rf runtime/Java/src/org/antlr/v4/runtime/tree/gui

### 4. Fix compiler error in the classes that contain `gui` classes:

 * `runtime/Java/src/org/antlr/v4/runtime/RuleContext.java`
 * `runtime/Java/src/org/antlr/v4/runtime/tree/Trees.java`

### 5. Build the project by doing:

    mvn clean install -DskipTests=true

### 6. Copy the runtime library into the Android project:

    cp runtime/Java/target/antlr4-runtime-4.3.jar /your/android/project/app/libs

The expression parser used in the demo app is [Mu](https://github.com/bkiers/Mu).

The Android Studio files are also checked in, so you should have little problems
opening the project with Android Studio and giving it a test run.

