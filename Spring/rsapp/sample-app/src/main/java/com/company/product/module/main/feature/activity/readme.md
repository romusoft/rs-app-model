Manage Activity feature.
Admin users can configure current and feature activities throughout the year. Examples of activities:
Boot camp for kids, adult Ministry, Church services, fund raising etc.
Activities are the driving force website content.
When users navigate to the company website they view default activity page configured by church organizors and administrators.

The activity feature is from the users perspective. It is self contained in a sense most component necessary to make that feature work resides
in this package.
The components are:
1. - MVVM    -> View Model object that is hydrated to provide basic data tha can be consumed by Java, Html and Javascript
1. - View    -> A view class that has boiler plate configuration properties for an Html page. It is tightly integrated with the MVVM class.
1. - MVC     -> Uses the ViewModel object to pass basic data to Html page
1. - Service -> Where the business logic of that feature resides.
1. - Api     -> Communication channel to read and persist data from the client. It uses the

Convention:
We prepend all views with the word view under the given feature package.
