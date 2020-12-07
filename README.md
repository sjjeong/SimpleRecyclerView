# SimpleRecyclerView
SimpleRecyclerView aims to help produce an easily usable implementation of a RecyclerView.Adapter and RecyclerView.ViewHolder  

## Attribute
|name|example|description|
|-|-|-|
|dino_items|bind:dino_items="@{vm.itemList}"|Item List to show in RecyclerView|
|dino_itemLayout|bind:dino_itemLayout="@{@layout/item_main}"|Item Layout to show in RecyclerView|
|dino_diffCallback|bind:dino_diffCallback="@{(Object)MainItem.Companion}"|DiffUtil.ItemCallback<T>|
|dino_eventHolder|bind:dino_eventHolder="@{eventHolder}"|Object that has a function for handling event separately from Item|
|dino_vm|bind:dino_vm="@{vm}"|Object that has a function for handling event separately from Item|
|dino_viewModel|bind:dino_viewModel="@{viewModel}"|Object that has a function for handling event separately from Item|
|dino_headerItem|bind:dino_headerItem="@{vm.headerItem}"|Item to show in Header|
|dino_headerItemLayout|bind:dino_headerItemLayout="@{@layout/item_header}"|Header Layout to show in RecyclerView|
|dino_footerItem|bind:dino_footerItem="@{vm.footerItem}"|Item to show in Footer|
|dino_footerItemLayout|bind:dino_footerItemLayout="@{@layout/item_footer}"|Footer Layout to show in RecyclerView|
|dino_itemSpace|dino_itemSpace="@{@dimen/space}"|Space for each item|
|dino_itemSpace|dino_itemSpace="@{\`8dp\`}"|Space for each item|
|dino_includeEdge|dino_includeEdge="@{true}"|Padding space for RecyclerView|
|dino_onLoad|dino_onLoad="@{() -> vm.loadItem()}"|Load more items event handling|
|dino_threshold|dino_threshold="@{10}"|A threshold of remaining items before they've hit the end|


## Dependency

[ ![Download](https://api.bintray.com/packages/sjjeong1225/maven/simplerecyclerview/images/download.svg) ](https://bintray.com/sjjeong1225/maven/simplerecyclerview/_latestVersion)

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.dino.library:simplerecyclerview:x.y.z'
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
## How to handle click listener of item
Deprecated, Use \"dino_eventHolder\" or \"dino_vm\" or \"dino_viewModel\"

### 1. Add higher-order function to Item class
```kotlin
data class Item(
    val title: String,
    val onClick: (Item) -> Unit
)
```

### 2. Add BindingAdapter to root ViewGroup in item_layout.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="item"
            type="com.dino.simplerecyclerview.model.Item" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:onClick="@{() -> item.onClick.invoke(item)}"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{item.title}" />

    </LinearLayout>
</layout>
```

## Multi ViewType

Just implement ItemViewType

```kotlin
data class Item1(
    val title: String,
    override val itemLayoutResId: Int = R.layout.item_1
) : ItemViewType

data class Item2(
    val title: String,
    override val itemLayoutResId: Int = R.layout.item_2
) : ItemViewType
```

## AdpaterPosition
Add adapterPosition variable to item layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="adapterPosition"
            type="int" />
    </data>

    ...
    
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