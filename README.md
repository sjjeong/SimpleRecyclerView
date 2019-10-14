# SimpleRecyclerView
SimpleRecyclerView aims to help produce an easily usable implementation of a RecyclerView.Adapter and RecyclerView.ViewHolder  

[![](https://jitpack.io/v/sjjeong/SimpleRecyclerView.svg)](https://jitpack.io/#sjjeong/SimpleRecyclerView)

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.github.sjjeong:SimpleRecyclerView:1.0.0'
}
```

## Enable DataBinding
SimpleRecyclerView use [DataBinding](https://developer.android.com/topic/libraries/data-binding)

To configure your app to use data binding, add the `dataBinding` element to your `build.gradle` file in the app module (not root `build.gradle` file), as shown in the following example:
```gradle
android {
    ...
    dataBinding {
        enabled = true
    }
}
```
## Usage

There is a [sample](https://github.com/sjjeong/SimpleRecyclerView/tree/master/app). Sample include architectures pattern such as mvp and mvvm.


### RecyclerView.Adapter
```xml
<data>
    <import type="java.util.List" />
    <import type="com.dino.simplerecyclerview.DataClass" />
    <variable
        name="items"
        type="List&lt;DataClass>" />
</data>

...

<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:itemLayout="@{@layout/item_layout}"
    bind:items="@{items}" />
```
### RecyclerView.SimpleAdapter
```xml
<data>
    <import type="java.util.List" />
    <import type="com.dino.simplerecyclerview.DataClass" />
    <variable
        name="items"
        type="List&lt;DataClass>" />
    <variable
        name="diffCallback"
        type="androidx.recyclerview.widget.DiffUtil.ItemCallback&lt;Object>" />
</data>

...

<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:diffCallback="@{diffCallback}"
    bind:itemLayout="@{@layout/item_layout}"
    bind:items="@{items}" />
```

### Don't set item, just set adapter
Just set null value in `bind:items` attribute.
```xml
<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:itemLayout="@{@layout/item_layout}"
    bind:items="@{null}" />
```
### And item set programmatically(Not Recommended)
kotlin
```kotlin
recyclerView.setItems(items)
```
java
```java
RecyclerViewExtKt.setItems(recyclerView, items)
```
This method has a timing issue.
If you call a `setItems` function in (`onCreate`, `onStart`, `onResume`), the set of Adapters in the RecyclerView is called afterwards.


License
--------

    Copyright 2019 Dino

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.