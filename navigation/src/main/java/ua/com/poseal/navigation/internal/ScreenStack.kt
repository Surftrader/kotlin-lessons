package ua.com.poseal.navigation.internal

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.os.ParcelCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ua.com.poseal.navigation.NavigationState
import ua.com.poseal.navigation.Route
import ua.com.poseal.navigation.Router

//@SuppressLint("ParcelCreator")
internal class ScreenStack(
    private val routes: SnapshotStateList<RouteRecord>,
) : NavigationState, Router, InternalNavigationState, Parcelable {

    private val eventsFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = Int.MAX_VALUE,
    )

    constructor(parcel: Parcel) : this(
        SnapshotStateList<RouteRecord>().also { newList ->
            ParcelCompat.readList(
                parcel,
                newList,
                RouteRecord::class.java.classLoader,
                RouteRecord::class.java,
            )
        }
    )

    // Instead of @SuppressLint("ParcelCreator")
    companion object CREATOR: Parcelable.Creator<ScreenStack?> {
        override fun createFromParcel(parcel: Parcel): ScreenStack? {
            return ScreenStack(parcel)
        }
        override fun newArray(size: Int): Array<ScreenStack?> {
            return arrayOfNulls(size)
        }
    }

    // realization methods of NavigationState
    override val isRoot: Boolean get() = routes.size == 1
    override val currentRoute: Route get() = routes.last().route
    override val currentUuid: String get() = routes.last().uuid

    // realization methods of Router
    override fun launch(route: Route) {
        routes.add(RouteRecord(route))
    }

    override fun pop() {
        val removedRouteRecord = routes.removeLastOrNull()
        if (removedRouteRecord != null) {
            eventsFlow.tryEmit(NavigationEvent.Removed(removedRouteRecord))
        }
    }

    override fun restart(route: Route) {
        routes.apply {
            routes.forEach {
                eventsFlow.tryEmit(NavigationEvent.Removed(it))
            }
            clear()
            add(RouteRecord(route))
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
