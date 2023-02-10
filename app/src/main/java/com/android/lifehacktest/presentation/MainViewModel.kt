package com.android.lifehacktest.presentation

import android.util.Log
import androidx.lifecycle.*
import com.android.lifehacktest.domain.models.Company
import com.android.lifehacktest.domain.models.CompanyInfo
import com.android.lifehacktest.domain.repository.CompanyRepository
import kotlinx.coroutines.launch
import com.android.lifehacktest.data.repository.*

class MainViewModel(private val repository: CompanyRepository) : ViewModel() {
    private var _companyInfo = MutableLiveData<CompanyInfo>()
    var companyInfo = _companyInfo as LiveData<CompanyInfo>

    private var _companies = MutableLiveData<List<Company>>()
    var companies = _companies as LiveData<List<Company>>

    private val _error = MutableLiveData<String>()
    val error = _error as LiveData<String>


    fun loadCompanies() {
        viewModelScope.launch {
            when (val res = repository.getCompanies()) {
                is Result.Error ->{
                    _error.value = res.throwable.message
                    Log.e("ERRR",_error.value.toString())
                }
                is Result.Success -> _companies.value = res.data
                else -> {}
            }
        }

    }

    fun loadCompanyInfo(id:String) {
        viewModelScope.launch {
            when (val res = repository.getCompanyInfo(id)) {
                is Result.Error -> {
                    _error.value = res.throwable.message
                    Log.e("ERRR",_error.value.toString())
                }
                is Result.Success -> _companyInfo.value = res.data
                else -> {}
            }
        }

    }

}








