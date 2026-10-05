package repository

import com.google.firebase.firestore.FirebaseFirestore
import com.project.dishnary.model.Items
import com.project.dishnary.model.Recipe
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

    suspend fun findRecipes(
        selectedIngredients: Set<String>
    ): List<Recipe> {

        val snapshot = firestore
            .collection("recipes")
            .get()
            .await()

        val recipes = snapshot.documents.mapNotNull {
            it.toObject(Recipe::class.java)
        }

        return recipes.filter { recipe ->

            val recipeIngredients = recipe.ingredients
                .map { it.lowercase().trim() }
                .toSet()

            val selected = selectedIngredients
                .map { it.lowercase().trim() }
                .toSet()

            val matched = recipeIngredients.intersect(selected)

            val percentage =
                matched.size.toDouble() / recipeIngredients.size

            percentage >= 0.50
        }
    }
}