# Minor
A bottom navigation view solution for Android. <br>
See: https://www.google.com/design/spec/components/bottom-navigation.html<br><br>
<img src="https://raw.githubusercontent.com/Batterii/Minor/master/screenshots/device-2016-05-13-180006.png" height="800" width="420"/>

## About
There are quite a few solutions for Bottom Navigation on Android, most of them don't allow for using ```Views``` as navigation
items.<br><br>
Minor does!<br><br>
This allows for any ```View``` to be used as a navigation item, including a ```TextView``` with a custom font (like an icon
font) 
<br><br>Yay!!!

### Gradle
```gradle

  allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

		dependencies {
	        compile 'com.github.Batterii:Minor:v1.2.3'
	}
```
##Usage
###Layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    tools:context="com.akiniyalocts.minorsample.MainActivity">

    <com.akiniyalocts.minor.MinorLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:minor_center_items="true"
        android:background="@color/colorPrimary">

        <com.akiniyalocts.minor.MinorView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:minor_selected="true"
            app:minor_title_selected_color="@color/colorAccent"
            app:minor_title="Home"
            app:minor_title_text_color="@android:color/white"
            app:minor_icon_view="@layout/minor_home"/>

        <com.akiniyalocts.minor.MinorView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:minor_title="Favorites"
            app:minor_title_text_color="@android:color/white"
            app:minor_icon_view="@layout/minor_home"/>

        <com.akiniyalocts.minor.MinorView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:minor_title="Lists"
            app:minor_title_text_color="@android:color/white"
            app:minor_icon_view="@layout/minor_home"/>

        <com.akiniyalocts.minor.MinorView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/last_home"
            android:background="?attr/selectableItemBackgroundBorderless"
            app:minor_title="Friends"
            app:minor_title_text_color="@android:color/white"
            app:minor_icon_view="@layout/minor_home"/>

        <com.akiniyalocts.minor.MinorView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:minor_title="Account"
            app:minor_title_text_color="@android:color/white"
            app:minor_icon_view="@layout/minor_home"/>

    </com.akiniyalocts.minor.MinorLayout>

</RelativeLayout>
```
###Attributes
####MinorView
```xml

    app:minor_selected="true" //default is false
    app:minor_title_selected_color="@color/colorAccent" // text color for your selected item
    app:minor_title="Home" // text for your item
    app:minor_title_text_color="@android:color/white" // text color for your unselected item
    app:minor_icon_view="@layout/minor_home"// the view (ImageView, Custom font TextView, etc.) 
                                            // inflated as an icon for your item
                                            // REQUIRED!!!a
```

####MinorLayout
```xml
    app:minor_center_items="true" //items are weighted evenly, and centered in the layout
    android:background="@color/colorPrimary" // set the background color for your layout

    app:layout_behavior="com.akiniyalocts.minor.behavior.MinorBehavior"//quick return scrolling behavior (similar to toolbar)

```

####Notifications
<img src="https://raw.githubusercontent.com/Batterii/Minor/master/screenshots/Screen%20Shot%202016-05-17%20at%2011.28.47%20AM.png"</>
```java
package com.akiniyalocts.minorsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.akiniyalocts.minor.MinorView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MinorView home = (MinorView)findViewById(R.id.last_home);

        home.addNotifcation(8);
    }
}
```

###Handling Clicks
Since your navigation items are just views, setting a simple ```OnClickListener``` on that view works!

###TODO
Handle click state within the view. Currently, clicking an item does not show an item as selected. You must apply the 
attribute, ```app:minor_selected="true"``` to show a ```MinorView``` as selected.
License
--------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
