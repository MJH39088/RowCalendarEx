package com.hmj3908.rowcalendarex

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.hmj3908.rowcalendarex.adapter.CalendarAdapter
import com.hmj3908.rowcalendarex.adapter.ParentAdapter
import com.hmj3908.rowcalendarex.databinding.ActivityMainBinding
import com.hmj3908.rowcalendarex.databinding.ItemRowBinding
import com.hmj3908.rowcalendarex.databinding.ItemSectionBinding
import com.hmj3908.rowcalendarex.model.CalendarDateModel
import com.hmj3908.rowcalendarex.utils.HorizontalItemDecoration
import com.hmj3908.rowcalendarex.utils.SectionItem
import com.skydoves.expandablelayout.expandableLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.exp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sdf = SimpleDateFormat("MMMM", Locale.KOREAN)
    private val cal = Calendar.getInstance(Locale.KOREAN)
    private val currentDate = Calendar.getInstance(Locale.KOREAN)
    private val dates = ArrayList<Date>()
    private lateinit var adapter: CalendarAdapter
    private val calendarList2 = ArrayList<CalendarDateModel>()
    private var isopened = false
    private lateinit var customadapter: ParentAdapter
    private lateinit var lowbinding: ItemSectionBinding
    lateinit var myApplication: MyApplication
    private var isSectionAdded = false
//    val myExpandableLayout = expandableLayout() {
//        setParentLayoutResource(R.layout.activity_detail)
//        setSecondLayoutResource(R.layout.activity_daily)
//        setSpinnerAnimate(true)
//        setSpinnerRotation(90)
//        setDuration(200)
//        setOnExpandListener { Toast.makeText(this@MainActivity,"is expanded: $it", Toast.LENGTH_SHORT).show() }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myApplication = application as MyApplication
        customadapter = ParentAdapter()
        binding = ActivityMainBinding.inflate(layoutInflater)
        lowbinding = ItemSectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAdapter()
        setUpClickListener()
        setUpCalendar()
//        setweekly()
        setUpExpandable()
//        setUpcustomrecyclerView()
    }

    private fun setUpExpandable() {

        with(binding) {
            expandable.setOnExpandListener {
                if (it) {
                    toast("expanded")
                } else {
                    toast("collapse")
                }
            }
            binding.tvWeekText.setOnClickListener {
//                myApplication.isexpanded2 = if (!myApplication.isexpanded2) {
//                    binding.tvWeekText.setText("주간 일정 접기")
////                    expandable.toggleLayout()
//
//                    !myApplication.isexpanded2
//                } else {
//                    binding.tvWeekText.setText("주간 일정 펼치기")
////                    expandable.toggleLayout()
//
//                    !myApplication.isexpanded2
//                }
//                customadapter.addSectionItem(
//                    SectionItem("", R.color.white, customArray, !myApplication.isexpanded2)
//                )
                if (!isSectionAdded) { // 섹션이 추가되지 않았다면
                    // ParentAdapter에 섹션 추가
                    addSection()
                    isSectionAdded = true
                }
                isopened = !isopened

                customadapter.setExpandableLayoutExpanded(isopened)

                binding.tvWeekText.text = if (isopened) "주간 일정 펼치기" else "주간 일정 접기"
//                customadapter.notifyItemChanged(0)
            }
        }
    }

    private fun addSection() {
        // ParentAdapter에 섹션 추가
        var customArray = arrayListOf<String>()
        for(i in 0..23) {
            customArray.add(i.toString())
        }
        customadapter.addSectionItem(
            SectionItem("", R.color.white, customArray)
        )
    }
    private fun setUpClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            setUpCalendar()
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)
            if (cal == currentDate)
                setUpCalendar()
            else
                setUpCalendar()
        }
    }

    private fun setUpcustomrecyclerView() {
        binding.customrecyclerView.adapter = customadapter
        var customArray = arrayListOf<String>()
        for(i in 0..23) {
            customArray.add(i.toString())
        }

//        customadapter.addSectionItem(
//            SectionItem("", R.color.white, customArray, myApplication.isexpanded2)
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title1", R.color.md_yellow_100, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title2", R.color.md_amber_700, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title3", R.color.md_orange_200, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title4", R.color.md_green_200, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title5", R.color.md_blue_100, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title6", R.color.md_blue_200, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title7", R.color.md_purple_100, arrayListOf("item0", "item1", "item2", "item3"))
//        )
//        customadapter.addSectionItem(
//            SectionItem("Title8", R.color.md_purple_200, arrayListOf("item0", "item1", "item2", "item3"))
//        )
    }

    private fun setweekly() {
        binding.tvWeekText.setOnClickListener {
            isopened = if (!isopened) {
                binding.tvWeekText.setText("주간 일정 접기")
                binding.recyclerView.visibility = View.VISIBLE
                !isopened
            } else {
                binding.tvWeekText.setText("주간 일정 펼치기")
                binding.recyclerView.visibility = View.INVISIBLE
                !isopened
            }
        }
    }

//    private fun setExpandableLayout() {
//        val myExpandableLayout = expandableLayout(baseContext)
//    }

    private fun setUpAdapter() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
        binding.recyclerView.addItemDecoration(HorizontalItemDecoration(spacingInPixels))
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        adapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->
            calendarList2.forEachIndexed { index, calendarModel ->
                calendarModel.isSelected = index == position
            }
            adapter.setData(calendarList2)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setUpCalendar() {
        val calendarList = ArrayList<CalendarDateModel>()
        binding.tvDateMonth.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList2.clear()
        calendarList2.addAll(calendarList)
        adapter.setData(calendarList)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}