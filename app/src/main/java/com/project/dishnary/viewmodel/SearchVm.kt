package com.project.dishnary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.dishnary.model.Items
import com.project.dishnary.sealedClasses.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        val current = _selectedItems.value.toMutableSet()
        if (!current.remove(item)) current.add(item)
        _selectedItems.value = current.toSet()
    }



}