# DynamicFeatureSample

#This project is a sample for dynamic feature

#Commands to build aab and test dynamic feature
1. gradlew bundleDebug
2. java -jar bundletool.jar build-apks --local-testing --bundle=..\DynamicFeatureSample\app\build\outputs\bundle\debug\app-debug.aab --output=..\DynamicFeatureSample\app\build\outputs\bundletool\app_poc.apks --overwrite
3. java -jar bundletool.jar install-apks --apks=..\DynamicFeatureSample\app\build\outputs\bundletool\app_poc.apks

#Command to build universal apk with dynamic feature included
1. gradlew packageDebugUniversalApk

# Uncomment //setTheme(R.style.DynamicActivityTheme) in DynamicFeatureActivity.kt to fix crash issue
