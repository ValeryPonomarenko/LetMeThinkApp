package me.vponomarenko.letmethink.data.repository

//import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Single
import me.vponomarenko.letmethink.data.datasource.listofstories.Stories
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 22.03.2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class RemoteStoriesRepository @Inject constructor(
//    private val firebaseDatabase: FirebaseDatabase
) : IRemoteStoriesRepository {

    companion object {
        private const val DB_NAME = "stories"
    }

    override fun loadStories(): Single<List<Story>> =
        Single
            .just(
                Stories.get()
            )
}