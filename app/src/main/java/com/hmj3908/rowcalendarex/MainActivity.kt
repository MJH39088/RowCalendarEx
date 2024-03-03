package com.hmj3908.rowcalendarex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.hmj3908.rowcalendarex.adapter.CalendarAdapter
import com.hmj3908.rowcalendarex.databinding.ActivityMainBinding
import com.hmj3908.rowcalendarex.model.CalendarDateModel
import com.hmj3908.rowcalendarex.utils.HorizontalItemDecoration
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sdf = SimpleDateFormat("MMMM", Locale.KOREAN)
    private val cal = Calendar.getInstance(Locale.KOREAN)
    private val currentDate = Calendar.getInstance(Locale.KOREAN)
    private val dates = ArrayList<Date>()

    private lateinit var adapter: CalendarAdapter
    private val calendarList2 = ArrayList<CalendarDateModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // UI 함수 초기화
        initView()
    }

    /**
     * Init UI
     */
    private fun initView() {

        lifecycleScope.launch {

            // 클릭 리스너 초기화
            setOnClickListener()

            // 캘린더 리싸이클러뷰 초기화 함수 초기화
            initCalendarRecyclerView()

            // 캘린더 관련 클릭리스너 함수 초기화
            setOnClickCalendarListener()

            // 캘린더 관련 데이터 초기화 함수
            initCalendarData()
        }
    }

    /**
     * SetOnClick 초기화
     */
    private fun setOnClickListener() {

        with(binding) {

            // 주간 일정 펼치기 초기화
            tvWeekText.text = "주간 일정 펼치기"
            tvWeekText.setOnClickListener {

                if ( tvWeekText.text == "주간 일정 펼치기" ) {

                    tvWeekText.text = "주간 일정 접기"
                    expandable.expand()
                } else {

                    tvWeekText.text = "주간 일정 펼치기"
                    expandable.collapse()
                }
            }
        }
    }

    /**
     * SetOnClick Calendar 초기화
     */
    private fun setOnClickCalendarListener() {

        with(binding) {

            // 다음 버튼 [ > ]
            ivCalendarNext.setOnClickListener {

                cal.add(Calendar.MONTH, 1)
                initCalendarData()
            }

            // 이전 버튼 [ < ]
            ivCalendarPrevious.setOnClickListener {

                cal.add(Calendar.MONTH, -1)
                if (cal == currentDate)
                    initCalendarData()
                else
                    initCalendarData()
            }
        }
    }

    /**
     * Init Calendar Data
     * @param Data
     */
    private fun initCalendarData() {

        with(binding) {

            // 데이터 초기화
            dates.clear()
            calendarList2.clear()

            // 월 단위 텍스트 초기화
            tvDateMonth.text = sdf.format(cal.time)

            val calendarList = ArrayList<CalendarDateModel>()
            val monthCalendar = cal.clone() as Calendar
            val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

            // 현재 선택된 이 달 예외처리
            while (dates.size < maxDaysInMonth) {

                // 이 달 데이터 생성
                dates.add(monthCalendar.time)
                calendarList.add(CalendarDateModel(monthCalendar.time))
                monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            // 캘린더 데이터 추가
            calendarList2.addAll(calendarList)

            // 어댑터 데이터 추가
            adapter.setData(calendarList)
        }
    }

    /**
     * Init Calendar RecyclerView
     * @param UI
     */
    private fun initCalendarRecyclerView() {

        with(binding) {

            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
            recyclerView.addItemDecoration(HorizontalItemDecoration(spacingInPixels))

            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)

            adapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->

                calendarList2.forEachIndexed { index, calendarModel ->

                    calendarModel.isSelected = index == position
                }

                adapter.setData(calendarList2)
            }

            recyclerView.adapter = adapter
        }
    }
}