For publishing existing app updates. Below are steps to help you through the process. Near the bottom are possible errors you may encounter in Google Play Console. GOOD LUCK!

If you lost the .jks file, you may need to get a new one from Google Play Console. 

You may need to update, refactor and debug the app. You will likely have to update Gradle, JVM, dependencies and any code that has changed. Do these before building and submitting the app.

>>> To sign the App, you must use the right key and certificate. Note the SHA-1 fingerprint if you upload the bundle with the wrong signing will be shown, it must match the one shown in Upload key certificate page. Use that cert like this:
1.	first get the certificate from google play console in 'Google Play Console > App > Setup > App 	signing > Upload key certificate'.
2.	in Android Studio, go to 'Settings > Tools > Server Certificates' and ADD the cert you downloaded
3.	notice the fingerprints should match the ones in Upload key certificate page in Google Play 	Console.
4.	apply the cert
5.	in build.gradl(Module:app), update the versionCode and versionName

>>> Generate a new SIGNED app bundle with the existing key .jks file:
1.	go to 'Build > Generate Signed App Bundle or APK > Android App Bundle (toggle on) > Next'
2.	in key store path add the .jks file, make sure it is located within the project files
3.	add the key store password, choose the key alias (typically key0) and add key password (likely 	same as key store password), the press 'Next'
4.	set the 'Destination Folder', select 'release' in the 'Build Variants' and finally create the app
5.	keep track of the bundle created, you may need to retry this process if you have any bugs or if 	Gradle is being difficult as usual

>>> In the Google Developer Play Console:
1.	go to the App's 'Production' page (if not there already). It has the rocket icon
2.	got to 'Releases'
3.	go to 'Edit Releases'
4.	in 'App bundles', select 'Upload'

>>> ERRORS you may get
> THE BUNDLE HAS NOT BEEN SIGNED. If when you upload the bundle after building it, you get an error saying it was not signed, make sure the right key ( the .JKS file) was used. You may see a private_key.pepk file, but thats for something else I forgot about, and at the time of writing the .jks file is working.
> THE SHA-1 FINGERPRINT CODE DOES NOT MATCH. Check the cert you are using for the project. See the above steps for adding the cert from Google Play Store. Add it to the server certificates page in settings.
> THE VERSION # IS ALREADY IN USE. Simply change the version number in gradle. 

(*Tangent Note, you may notice in gradle the signingConfigs{ debug{} } has the storePassword and keyPassword. I'm not sure if this matters, but try changing this as well. I may have lost track of this in the days trying to get this app update and bundle signing working properly)




