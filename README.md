# DynamicFeatureSample

This project is a sample for dynamic feature module

Commands to build aar and test
1. gradlew bundleDebug
2. java -jar bundletool.jar build-apks --local-testing --bundle=..\DynamicFeatureSample\app\build\outputs\bundle\debug\app-debug.aab --output=..\DynamicFeatureSample\app\build\outputs\bundletool\app_poc.apks --overwrite
java -jar bundletool.jar install-apks --apks=..\DynamicFeatureSample\app\build\outputs\bundletool\app_poc.apks

Command to build universal apk with dynamic feature included
gradlew packageDebugUniversalApk
