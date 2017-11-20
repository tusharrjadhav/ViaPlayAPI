# ViaPlayAPI
Exploring ViaPlay open API to show its Pages on Android application  using Android "Room"  Database Object Mapping library, Android lifecycle component ViewModel using MVVM design pattern

This Application is mainly focus on Android Lifecycle compoent ViewModel used for MVVM design pattern.

I have create App which uses ViaPlay API to show its data as “Page” which has different “Sections”. Each section has different sub sections. This app is worked on both mode online and offline mode.

I have created two activity, with simple UI as Recycle view.

Project APIs:

- UX component
MainActivity - For Root page.
SectionPageActivity - For Section Pages
RecycleView - For showing section list

- Network component
Retrofit API - For network connection.

- Database component
Room - persistence library provides an abstraction layer over SQLite

- Lifecycle component 
ViewModel - Providing data to View and handling events from View

- Unit Testing
PageViewModelTest

- Instrumentation Test
PageDaoTest 



Resources:
Android-architecture : https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live/




