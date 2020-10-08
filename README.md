# Movies Follower ![alt text](https://i.imgur.com/0gfMihl.png)

Movies Follower is an app that lets you discover movies and tv shows using the moviesdb API.

![alt text](https://i.imgur.com/HvDV6Ak.jpg)

- You can view movies or tv shows with their rating, genres, release date and how many people voted for them.
- You can search for movies/tvShows by typing the name and seeing the results instantly.
- You can discover movies/tvShows by their genres.
- You can use the **Filter Tool** to control your search in a flexible way.
- You can store your favorite movies/tvShows in a local database and view them whenever you want.
- You can swipe to delete your favorite movies/tvShows, and you can undo that delete.
- You can watch trailers and videos on the movie/tvShow page.

# I used 
- The **MVVM** architecture with [View Models](https://developer.android.com/topic/libraries/architecture/viewmodel) and [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
- [Retrofit](https://github.com/square/retrofit) to load data from the moviesdb API
- [Room persistence library](https://developer.android.com/topic/libraries/architecture/room) to store the movies/tvShows in a local database
- The Youtube API to start intents in order to show movies/tvShow trailers
- [Glide](https://bumptech.github.io/glide/) to load images

**For the UI -** 
- Activities (I used exactly four activities, one activity for *filter page*, another one for *the movie/tvShow page*, another one for *the search page*, and a *main activity*)
- Layouts (Constraints layouts, Relative Layouts, Linear Layouts, AppBarLayout, CollapsingToolbarLayout)
- Fragments
- RecyclerViews
- Bottom Navigation View

# Things I know I should improve or include in the future

- Start using Fragment factory, and get rid of the methods I declared inside of my fragments in order to pass arguments such as *setAction* and *setQueries* that are inside of SearchTvFrament..etc.
- Start using Dagger2 for dependency injection.
- Add the feature where the app loads more data when getting to the end of the recycler view.
- Fix the swipe-to-delete bug. The problem is, I can't delete two items at the same time, I must wait until the snackbar disappears, if I don't, the last deleted item will appear again.
- Add unit and UI tests for the app.
