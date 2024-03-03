//package com.hmj3908.rowcalendarex
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.hmj3908.rowcalendarex.adapter.ParentAdapter
//import com.hmj3908.rowcalendarex.databinding.FragmentWeeklyBinding
//import com.hmj3908.rowcalendarex.utils.SectionItem
//
//class WeeklyFragment : Fragment() {
//    private var _binding: FragmentWeeklyBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var parentAdapter: ParentAdapter
//    private var isExpanded = false
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentWeeklyBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Initialize RecyclerView
//        parentAdapter = ParentAdapter()
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = parentAdapter
//        }
//
//        // Add data to RecyclerView
//        addSectionData()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    fun toggleExpandableLayout() {
//        isExpanded = !isExpanded
//        // expandableLayout 열거나 닫기
//    }
//
//    private fun addSectionData() {
//        // Add your section data to the adapter
//        // For example:
//        // val sectionItem = SectionItem("Section Title", R.color.white, listOf("Item 1", "Item 2", "Item 3"))
//        // parentAdapter.addSectionItem(sectionItem)
//        val customArray = (0..23).map { it.toString() }
//        parentAdapter.addSectionItem(
//            SectionItem("", R.color.white, customArray)
//        )
//
//    }
//}