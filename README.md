# DynamicFeatureSample

#This project is a sample for dynamic feature

#Commands to build aab and test dynamic feature
1. gradlew bundleDebug
2. java -jar bundletool.jar build-apks --local-testing --bundle=app/build/outputs/bundle/debug/app-debug.aab --output=app/build/outputs/bundletool/app_poc.apks --overwrite --connected-device
3. java -jar bundletool.jar install-apks --apks=app/build/outputs/bundletool/app_poc.apks

#Command to build universal apk with dynamic feature included
1. gradlew packageDebugUniversalApk
