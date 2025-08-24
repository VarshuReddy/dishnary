package repository

import com.google.firebase.firestore.FirebaseFirestore
import com.project.dishnary.model.Items
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ItemsRepo @Inject constructor(private val firestore: FirebaseFirestore) {

    suspend fun getIngredients(): List<Items>{
        return try{
            val snapshot = firestore.collection("Ingredients").
                          document("Ingredients").get().await()

            snapshot.data?.map{
                Items(name = it.key,
                    items = (it.value as List<*>).filterIsInstance<String>()
                )
            }?.reversed() ?: emptyList()


        }catch (e: Exception){
            e.printStackTrace()
            emptyList()
        }

    }
}