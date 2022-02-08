package app.iandis.camelmovies.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class ViewModelBase<T>(initialState: T) : ViewModel() {
    private val _state: MutableStateFlow<T> = MutableStateFlow(initialState)

    val state: StateFlow<T> get() = _state

    protected fun emit(nextState: T) {
        _state.tryEmit(nextState)
    }
}