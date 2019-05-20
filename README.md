# Statik

[![jcenter](https://api.bintray.com/packages/kittinunf/maven/Statik/images/download.svg)](https://bintray.com/kittinunf/maven/Statik/_latestVersion)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.10-blue.svg)](http://kotlinlang.org)
[![Build Status](https://travis-ci.org/kittinunf/Statik.svg?branch=master)](https://travis-ci.org/kittinunf/Statik)

A Kotlin DSL for Android made to display a collection of data-backed views concisely and elegantly.

## Overview

Do you want to show a list of items in Android and wish there was a faster or simpler way?

Look no further, Statik is designed for you!

Statik is a data-oriented Kotlin DSL, backed by the standard RecyclerView. 

Just describe your data, set it into the provided adapter and the rest will be handled by Statik, it's that simple!

Since Statik is backed by RecyclerView, you can display any type of view.

## Installation

### Gradle

``` Groovy
repositories {
    jcenter()
}

dependencies {
    implementation 'com.github.kittinunf.statik:statik:<latest-version>'
}
```

## Usage

As an example, let's display a simple list.


First, setup a simple RecyclerView in your layout

```xml
 <android.support.v7.widget.RecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="android.support.v7.widget.LinearLayoutManager"/> 
```

Then use the Statik Kotlin DSL to describe how your list should look like

```kotlin
// describe a header
val h1 = textHeader {
            text = "Header"
            onClickListener = {
                println("Header is clicked")
            }
         }
         
// describe the different rows  
val r1 = textRow {
            text = "Row 1"
         }      
         
val r2 = textRow {
            text = "Row 2"
         }          
         
val r3 = textRow {
            text = "Row 3"
         }          
         
// describe a footer        
val f1 = textFooter {
            text = "Footer"
            onClickListener = {
                println("Footer is clicked")
            }
         }
        
// describe a section for the above
val s1 = section {
            header(h1)
            rows(r1, r2, r3)
            footer(f1)
         }
```    

Set the section(s) into the adapter    
```kotlin
val statikAdapter = adapter {
                        sections(s1)
                    }    
```    

That's all! Just set the adapter in the RecyclerView
```kotlin
list.adapter = statikAdapter
```

### Advanced Usage

For additional examples and use cases, make sure to check out the sample demo.

Currently, examples include: 

 - [x] Multiple view types with respective event listeners
 - [x] Multiple sections
 - [x] Adding views dynamically
 
## Credits

Statik is brought to you by [contributors](https://github.com/kittinunf/Statik/graphs/contributors).

## Licenses

Statik is released under the [MIT](https://opensource.org/licenses/MIT) license.
