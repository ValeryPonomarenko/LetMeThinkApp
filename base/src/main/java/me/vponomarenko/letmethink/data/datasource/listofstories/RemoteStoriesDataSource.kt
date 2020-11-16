package me.vponomarenko.letmethink.data.datasource.listofstories

//import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class RemoteStoriesDataSource @Inject constructor(
//    private val firebaseDatabase: FirebaseDatabase
) : IListOfStoriesDataSource {

    companion object {
        private const val DB_NAME = "stories"
    }

    override fun count(): Single<Int> {
        throw IllegalStateException("Remote data source cannot say how many stories it has")
    }

    override fun loadFavoriteStories(): Flowable<List<Story>> {
        throw IllegalStateException("Remote data source cannot return a favorite shelf")
    }

    override fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>> =
        Single
            .just(Stories.get())
            .toFlowable()
//        firebaseDatabase
//            .getReference(DB_NAME)
//            .asSingle {
//                it.children.map {
//                    val dirtyModel =
//                        it.getValue(DirtyStory::class.java) ?: throw TypeCastException()
//                    dirtyModel.toStory()
//                }
//            }
//            .toFlowable()

    override fun saveStories(stories: List<Story>): Single<Boolean> {
        throw IllegalStateException("Remote data source cannot save items")
    }
}