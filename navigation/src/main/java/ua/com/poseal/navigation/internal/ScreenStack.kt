package ua.com.poseal.navigation.internal

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ua.com.poseal.navigation.NavigationState
import ua.com.poseal.navigation.Route
import ua.com.poseal.navigation.Router

@SuppressLint("ParcelCreator")
internal class ScreenStack(
    private val routes: SnapshotStateList<Route>,
) : NavigationState, Router, InternalNavigationState,Parcelable {

    private val eventsFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = Int.MAX_VALUE,
    )

//    constructor(parcel: Parcel) : this(
//        SnapshotStateList<Route>().also { newList ->
//            ParcelCompat.readList(
//                parcel,
//                newList,
//                Route::class.java.classLoader,
//                Route::class.java,
//            )
//        }
//    )

    // realization methods of NavigationState
    override val isRoot: Boolean get() = routes.size == 1
    override val currentRoute: Route get() = routes.last()

    // realization methods of Router
    override fun launch(route: Route) {
        routes.add(route)
    }

    override fun pop() {
        val removedRoute = routes.removeLastOrNull()
        if (removedRoute != null) {
            eventsFlow.tryEmit(NavigationEvent.Removed(removedRoute))
        }
    }

    override fun restart(route: Route) {
        routes.apply {
            routes.forEach {
                eventsFlow.tryEmit(NavigationEvent.Removed(it))
            }
            clear()
            add(route)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(routes)
    }

    override fun listen(): Flow<NavigationEvent> {
        return eventsFlow
    }
}
