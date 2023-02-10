package com.android.lifehacktest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.lifehacktest.R
import com.android.lifehacktest.data.repository.CompanyRepositoryImpl
import com.android.lifehacktest.data.retrofit.Common
import com.android.lifehacktest.presentation.MainViewModel
import kotlinx.android.synthetic.main.fragment_all_companies.*

class AllCompaniesFragment : Fragment() {
    private var adapter: CompaniesAdapter? = null
    private val viewModel = MainViewModel(CompanyRepositoryImpl(Common.retrofitService))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CompaniesAdapter(requireContext(),
            fun(id: String) {
                val bundle = bundleOf("id" to id)
                requireView().findNavController()
                    .navigate(R.id.action_allCompaniesFragment_to_companyFragment, bundle)
            }
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(50)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.setPadding(10, 30, 10, 30)
        recyclerView.adapter = adapter
        showWaiting(true)



        viewModel.loadCompanies()

        viewModel.companies.observe(viewLifecycleOwner) {
            showWaiting(false)
            adapter?.itemList = it

        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Что-то пошло не так...", Toast.LENGTH_SHORT).show()
            /* requireView().findNavController().
             navigate(R.id.action_resultFragment_to_searchFragment)*/
        }
    }

    private fun showWaiting(inProgress: Boolean) {
        progressBar.isVisible = inProgress
        recyclerView.isVisible = !inProgress
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}