# SimpleRecyclerView
SimpleRecyclerView aims to help produce an easily usable implementation of a RecyclerView.Adapter and RecyclerView.ViewHolder  

## Dependency
Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.dino.library:simplerecyclerview:0.0.2'
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

### item_layout.xml
```xml
<data>
    <variable
        name="item" <- very important
        type="com.your.package.YourItem"/>
</data>

<TextView
    android:text="@{item.title}"/>
<TextView
    android:text="@{item.content}"/>
```
if your item name of item_layout.xml is not "item", can't bind item in ViewHolder

### RecyclerView.Adapter
```xml
<data>
    <import type="java.util.List" />
    <import type="com.your.package.YourItem" />
    <variable
        name="items"
        type="List&lt;YourItem>" />
</data>

...

<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:dino_itemLayout="@{@layout/item_layout}"
    bind:dino_items="@{items}" />
```
### RecyclerView.SimpleAdapter
```xml
<data>
    <import type="java.util.List" />
    <import type="com.your.package.YourItem" />
    <variable
        name="items"
        type="List&lt;YourItem>" />
    <variable
        name="diffCallback"
        type="androidx.recyclerview.widget.DiffUtil.ItemCallback&lt;Object>" />
</data>

...

<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:dino_diffCallback="@{diffCallback}"
    bind:dino_itemLayout="@{@layout/item_layout}"
    bind:dino_items="@{items}" />
```

### Don't set item, just set adapter
Just set null value in `bind:items` attribute.
```xml
<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    bind:dino_itemLayout="@{@layout/item_layout}"
    bind:dino_items="@{null}" />
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

## MVVM Sample

### 1. create item_layout.xml
CustomModel is enum class
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="item"
            type="com.dino.simplerecyclerview.model.CustomModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{item.title}" />

    </LinearLayout>
</layout>
```
### 2. create CustomViewModel
```kotlin
class CustomViewModel : ViewModel() {

    val customModelItems = ObservableField<List<CustomModel>>()

    init {
        createNewItem()
    }

    fun createNewItem() {
        customModelItems.set(CustomModel.values().toList())
    }
}
```
### 3. set list item in activity.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:bind="http://schemas.android.com/apk/res-auto">
    <data>
        <variable
            name="vm"
            type="com.dino.simplerecyclerview.ui.CustomViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
            bind:dino_itemLayout="@{@layout/item_layout}"
            bind:dino_items="@{vm.customModelItems}" />

    </LinearLayout>
</layout>
```
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