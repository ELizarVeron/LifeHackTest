package com.android.lifehacktest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.Guideline
import androidx.core.view.children

import androidx.core.view.isVisible
import com.android.lifehacktest.R
import com.android.lifehacktest.data.repository.CompanyRepositoryImpl
import com.android.lifehacktest.data.retrofit.Common
import com.android.lifehacktest.domain.models.CompanyInfo
import com.android.lifehacktest.presentation.MainViewModel
import kotlinx.android.synthetic.main.fragment_company.*


class CompanyFragment : Fragment() {

    private val viewModel = MainViewModel(CompanyRepositoryImpl(Common.retrofitService))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id  =arguments?.getString("id")

        showWaiting(true)

        id?.let { viewModel.loadCompanyInfo(it) }

        viewModel.companyInfo.observe(viewLifecycleOwner) {
            showWaiting(false)
            init(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {

            Toast.makeText(requireContext(),"Что-то пошло не так...", Toast.LENGTH_SHORT).show()

        }
    }

    private fun init(companyInfo: CompanyInfo){


        tv_name.text =if( companyInfo.name.isNullOrEmpty()) "нет данных" else companyInfo.name
        tv_desc.text = if( companyInfo.description.isNullOrEmpty()) "нет данных" else companyInfo.description
        tv_lon.text =  if(companyInfo.lon ==0.0)"нет данных" else companyInfo.lon.toString()
        tv_lat.text =  if(companyInfo.lat ==0.0)"нет данных" else companyInfo.lat.toString()
        tv_www.text = if( companyInfo.www.isNullOrEmpty()) "нет данных" else companyInfo.www
        tv_tel.text = if( companyInfo.phone.isNullOrEmpty()) "нет данных" else companyInfo.phone


    }

    private fun showWaiting(inProgress:Boolean){
        if(inProgress){
            progressBar.isVisible = true
            for(element in constraint.children){
                if(element !is Guideline){
                    element.isVisible = false
                }

            }
        }else{
            progressBar.isVisible = false
            for(element in constraint.children){
                if(element !is Guideline){
                    element.isVisible = true
                }
            }
        }


     /*   progressBar.isVisible = inProgress
        recyclerView.isVisible =!inProgress*/
    }



}