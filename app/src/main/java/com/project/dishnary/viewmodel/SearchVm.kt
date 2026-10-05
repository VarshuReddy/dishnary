package com.project.dishnary.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.dishnary.model.Items
import com.project.dishnary.model.Recipe
import com.project.dishnary.sealedClasses.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.ItemsRepo
import javax.inject.Inject

@HiltViewModel
class SearchVm @Inject constructor(val repo: ItemsRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Items>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Items>>> = _uiState

    private val _selectedItems = MutableStateFlow<Set<String>>(emptySet())
            val selectedItems:StateFlow<Set<String>> = _selectedItems

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val data = repo
                    .getIngredients()
                _uiState.value = UiState.Success(data)
            }catch (e: Exception){
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun clearSelection() {
        _selectedItems.value = emptySet()
    }

    fun toggleSelection(item: String) {
            _selectedItems.update { current ->
                if (item in current) {
                    current - item
                } else {
                    current + item
                }
        }
    }

    private val _expanded = mutableStateMapOf<String, Boolean>()
    val expanded : Map<String, Boolean> = _expanded

    fun toggleExpanded(category: String) {
        val current = _expanded[category] ?: true   // assume true if not stored
        _expanded[category] = !current
    }

    private val _recipeState =
        MutableStateFlow<UiState<List<Recipe>>>(UiState.Loading)

    val recipeState: StateFlow<UiState<List<Recipe>>> =
        _recipeState

    fun searchRecipes() {

        val selected = _selectedItems.value

        viewModelScope.launch {

            try {

                _recipeState.value = UiState.Loading

                val recipes =
                    repo.findRecipes(selected)

                _recipeState.value =
                    UiState.Success(recipes)

            } catch (e: Exception) {

                _recipeState.value =
                    UiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }

}