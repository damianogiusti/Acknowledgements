Acknowledger
==============

Acknowledger is a library which aims to make easy to add licensing screens to an Android app.

Download
--------
Step 1. Add in your root build.gradle at the end of repositories:
```groovy
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2: Add the dependency
```groovy
compile 'com.github.damianogiusti:Acknowledger:master-SNAPSHOT'
```

Usage
-----

Add a JSON file to the raw resources of your project, with the following structure:
```json
[
  {
    "name": "Artifact name",
    "url": "http://link.to.artifact/",
    "copyright": "Copyright YYYY The Artifact Authors",
    "license": "apache2"
  },
  ...
]
```

Here is the list of strings that are allowed in the `license` field of the JSON:
- `"apache2"`: The Apache 2.0 License
- `"bsd2"`: The 2-Clause BSD License
- `"bsd3"`: The 3-Clause BSD License
- `"cc3"`: Creative Commons 3.0
- `"cc3NoDerivativeWorks"`: Creative Commons 3.0 No Derivative Works
- `"cc3ShareAlike"`: Creative Commons 3.0 Share Alike
- `"eclipse"`: Eclipse Public License (EPL)
- `"gnuGpl2"`: GNU General Public License 2.0
- `"gnuGpl3"`: GNU General Public License 3.0
- `"gnuLgpl21"`: `"gnuLgpl3"`: GNU Lesser General Public License 2.1
- `"gnuLgpl3"`: GNU Lesser General Public License 3.0
- `"isc"`: The ISC License
- `"mit"`: The MIT License
- `"mozilla11"`: Mozilla Public License version 1.1
- `"mozilla2"`: Mozilla Public License version 2.0
- `"sil"`: SIL Open Font License

Then, in your Java code, the thing is made simply:
```java
        String myFabulousFormattedLicensingHTML =
                        Acknowledger.with(this)
                        .load(R.raw.licenses)
                        .asHtml();
```

Or maybe you directly want to show the HTML string into a WebView:

```java
        WebView webView = (WebView) findViewById(R.id.webView);

        Acknowledger.with(this)
                .load(R.raw.licenses)
                .into(webView);
```

Credits
-------

This library was born as a fork of [LicensesDialog][1]

License
-------

    Copyright 2017 Damiano Giusti

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://github.com/PSDev/LicensesDialog/